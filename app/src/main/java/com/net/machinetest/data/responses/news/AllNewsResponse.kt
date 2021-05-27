package com.net.machinetest.data.responses.news


import com.google.gson.annotations.SerializedName

data class AllNewsResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    var mapArticle: Map<String, List<Article>>
)