package com.demo.nytimes.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Model class to hold most popular article data
 *
 * @property storyId id of story
 * @property storyTitle title of story
 * @property author Author of teh story
 * @property publishedDate published date of story
 */
data class MostPopularArticleListModel(
    @SerializedName("id") val storyId: Long,
    @SerializedName("title") val storyTitle: String,
    @SerializedName("byline") val author: String,
    @SerializedName("published_date") val publishedDate: Date
)