package com.demo.nytimes.network

import com.demo.nytimes.network.model.MostPopularArticleModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * interfcae for retrofit APi call
 *
 */
interface NYApiInterface {

    /**
     * get list of MostPopularListModel
     *
     * @param apiKey APi key
     * @return MostPopularListModel
     */
    @GET("mostpopular/v2/mostviewed/all-sections/{Id}.json")
    suspend fun getMostPopularData(
        @Path("Id") value: String,
        @Query("api-key") apiKey: String
    ): Response<MostPopularArticleModel>

}