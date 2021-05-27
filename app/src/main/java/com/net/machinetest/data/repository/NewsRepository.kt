package com.net.machinetest.data.repository

import com.net.machinetest.data.network.NewsApi
import com.net.machinetest.data.responses.news.AllNewsResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi,
) : BaseRepository(api) {

    suspend fun getData() = safeApiCall {
        val response = api.getData()
        filterData(response)
    }

    private fun filterData(response: AllNewsResponse): AllNewsResponse {
        val data = response.articles.groupBy {
            it.source.name
        }
        response.mapArticle = data
        return response
    }
}