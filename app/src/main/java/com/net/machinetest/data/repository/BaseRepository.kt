package com.net.machinetest.data.repository

import com.net.machinetest.data.network.BaseApi
import com.net.machinetest.data.network.SafeApiCall

abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

}