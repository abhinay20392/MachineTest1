package com.net.machinetest.data.responses.news


import com.google.gson.annotations.SerializedName
import com.net.machinetest.data.responses.news.Article
import java.io.Serializable

data class Source(
    @SerializedName("id")
    val id: String ,
    @SerializedName("name")
    val name: String,
    val list: MutableList<Article>
): Serializable