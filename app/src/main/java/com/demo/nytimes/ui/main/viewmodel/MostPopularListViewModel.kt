package com.demo.nytimes.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.demo.nytimes.network.model.RetrofitResponse
import com.demo.nytimes.repository.NyAppRepository
import com.demo.nytimes.ui.main.view.TAG
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * View model for most popular article
 *
 * @property nyAppRepository NyAppRepository
 */
class MostPopularListViewModel @Inject constructor(private val nyAppRepository: NyAppRepository) :
    ViewModel() {

    fun getMostPopularData(daysValue: String) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            try {
                emit(RetrofitResponse.loadingData())
                val response = nyAppRepository.getMostPopularData(daysValue)
                if (response.isSuccessful) {
                    emit(RetrofitResponse.successResponse(response.body()))
                } else {
                    Log.e(TAG, "exception${response.errorBody()}")
                    emit(RetrofitResponse.failureResponse(response.errorBody().toString()))
                }
            } catch (exception: Exception) {
                emit(RetrofitResponse.failureResponse(exception.message.toString()))
                Log.e(TAG, "exception ${exception.localizedMessage}")
            }
        }
}