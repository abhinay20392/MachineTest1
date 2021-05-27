package com.net.machinetest.data.network

import com.net.machinetest.data.responses.news.AllNewsResponse
import retrofit2.http.GET

interface NewsApi : BaseApi {

    @GET("v2/everything?q=apple&from=2021-05-25&to=2021-05-25&sortBy=popularity&apiKey=606348ccbeac4cb59317509dc42891b3")
    suspend fun getData(): AllNewsResponse
}