package com.demo.nytimes.network.model

import com.google.gson.annotations.SerializedName

/**
 * class hold data related popular article
 *
 * @property count count of data recieved
 * @property mostPopularList list of MostPopularArticleListModel
 */
class MostPopularArticleModel(
    @SerializedName("num_results") var count: String,
    @SerializedName("results") var mostPopularList:
    List<MostPopularArticleListModel>
)