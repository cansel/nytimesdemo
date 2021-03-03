package com.demo.nytimes.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.demo.nytimes.component.DaggerTestNyAppComponent
import com.demo.nytimes.module.TestNetworkModule
import com.demo.nytimes.network.model.MostPopularArticleListModel
import com.demo.nytimes.network.model.MostPopularArticleModel
import com.demo.nytimes.network.model.ResponseType
import com.demo.nytimes.network.model.RetrofitResponse
import com.demo.nytimes.repository.NyAppRepository
import com.demo.nytimes.ui.main.viewmodel.MostPopularListViewModel
import com.demo.nytimes.utilities.Constants
import com.demo.nytimes.utils.MainCoroutinesRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.*
import org.junit.rules.TestRule
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class TestPopularViewModel {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    var nyAppRepository : NyAppRepository = mockk()
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()
    @Inject
    lateinit var model: MostPopularListViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        val component = DaggerTestNyAppComponent.builder()
            .retrofitNetworkModule(TestNetworkModule())
            .build()
        component.inject(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun successViewModel() = runBlockingTest {
            val fakeResponse = Response.success(
                MostPopularArticleModel(
                    "104",
                    arrayListOf(
                        MostPopularArticleListModel(
                            12222, "chetan", "chetan" +
                                    "asnel", Date("12/05/2019")
                        )
                    )
                )
            )

            coEvery { nyAppRepository.getMostPopularData(Constants.SECTION_VALUE_30) } returns fakeResponse
            val response = nyAppRepository.getMostPopularData(Constants.SECTION_VALUE_30)
            val resp: RetrofitResponse<ResponseType> = RetrofitResponse.
            loadingData()
            Assert.assertEquals(ResponseType.LOADING, resp.responseType)
            model.getMostPopularData(Constants.SECTION_VALUE_30).observeForTesting {
                Assert.assertEquals("104", response.body()!!.count)
                Assert.assertEquals(true, response.isSuccessful)
                Assert.assertNotEquals(ResponseType.FAILURE,
                    RetrofitResponse.successResponse(response).responseType)

            }
        }


    @ExperimentalCoroutinesApi
    @Test
    fun errorViewModel() = runBlockingTest{

            coEvery { nyAppRepository.getMostPopularData("30") } returns Response.error(
                400,
                "{\"error\":[\"error found\"]}"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            val response = nyAppRepository.getMostPopularData("30")
            val resp: RetrofitResponse<ResponseType> = RetrofitResponse.
            failureResponse("something wrong")
            model.getMostPopularData("30").observeForTesting {
                Assert.assertEquals(400, response.code())
                Assert.assertEquals(resp.errorMessage, "something wrong")
                Assert.assertEquals(ResponseType.FAILURE, resp.responseType)
            }
        }


    fun <T> LiveData<T>.observeForTesting(block: () -> Unit) {
        val observer = Observer<T> { }
        try {
            observeForever(observer)
            block()
        } finally {
            removeObserver(observer)
        }
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}