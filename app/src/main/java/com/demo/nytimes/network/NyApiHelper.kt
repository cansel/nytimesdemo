package com.demo.nytimes.network

import com.demo.nytimes.network.model.MostPopularArticleModel
import com.demo.nytimes.utilities.Constants
import retrofit2.Response
import javax.inject.Inject

/**
 * Api helper to fetch data from network
 *
 * @property nyApiInterface NYApiInterface
 */
class NyApiHelper @Inject constructor(private val nyApiInterface: NYApiInterface) :
    NetworkApiIntf {

    override suspend fun getMostPopularData(daysValue: String):
            Response<MostPopularArticleModel> {
        return nyApiInterface.getMostPopularData(daysValue, Constants.API_KEY)
    }
}