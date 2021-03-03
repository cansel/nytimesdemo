package com.demo.nytimes.network

import com.demo.nytimes.network.model.MostPopularArticleModel
import retrofit2.Response

/**
 * Network API interface for retrofit
 *
 */
interface NetworkApiIntf {

    suspend fun getMostPopularData(daysValue: String): Response<MostPopularArticleModel>
}