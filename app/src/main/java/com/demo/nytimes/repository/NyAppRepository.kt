package com.demo.nytimes.repository

import androidx.annotation.WorkerThread
import com.demo.nytimes.network.NyApiHelper
import javax.inject.Inject

/**
 * repository class for get data from network
 *
 * @property apiHelper
 */
class NyAppRepository @Inject constructor(private val apiHelper: NyApiHelper) {
    @WorkerThread
    suspend fun getMostPopularData(daysValue: String) =
        apiHelper.getMostPopularData(daysValue)
}

