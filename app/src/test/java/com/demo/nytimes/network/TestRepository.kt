package com.demo.nytimes.network

import com.demo.nytimes.component.DaggerTestNyAppComponent
import com.demo.nytimes.module.TestAppModule
import com.demo.nytimes.module.TestNetworkModule
import com.demo.nytimes.network.model.MostPopularArticleListModel
import com.demo.nytimes.network.model.MostPopularArticleModel
import com.demo.nytimes.repository.NyAppRepository
import com.demo.nytimes.utilities.Constants
import com.demo.nytimes.utils.MainCoroutinesRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.*
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class TestRepository {

    var nyAppRepository : NyAppRepository = mockk()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        val component = DaggerTestNyAppComponent.builder()
            .retrofitNetworkModule(TestNetworkModule())
            .appModule(TestAppModule())
            .build()
        component.inject(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun testSuccess() = runBlocking(){
        val fakeResponse = Response.success(
            MostPopularArticleModel(
                "10",
                arrayListOf(
                    MostPopularArticleListModel(
                        12222, "chetan", "chetan" +
                                "asnel", Date("12/05/2019")
                    ),
                    MostPopularArticleListModel(
                        22222, "micheal", "mikepol",
                        Date("12/09/2019")
                    )
                )
            )
        )
        coEvery { nyAppRepository.getMostPopularData(
            Constants.SECTION_VALUE_7)  } returns  fakeResponse
        val response = nyAppRepository.getMostPopularData(Constants.SECTION_VALUE_7)

        Assert.assertEquals("10", response.body()!!.count)
        Assert.assertEquals("micheal", response.body()!!.mostPopularList.get(1).
        storyTitle)
        Assert.assertEquals("mikepol", response.body()!!.mostPopularList.get(1).
        author)
        Assert.assertEquals(
            Date("12/09/2019"),
            response.body()!!.mostPopularList.get(1).publishedDate
        )
        Assert.assertEquals(22222, response.body()!!.mostPopularList.get(1).
        storyId)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testFailure() = coroutinesRule.runBlockingTest {
        coEvery { nyAppRepository.getMostPopularData(Constants.SECTION_VALUE_1) } returns Response.error(
            400,
            "{\"error\":[\"error found\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val response = nyAppRepository.getMostPopularData(Constants.SECTION_VALUE_1)
        Assert.assertEquals(400, response.code())
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
        coroutinesRule.cleanupTestCoroutines()
    }
}
