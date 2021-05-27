package com.net.machinetest.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.net.machinetest.data.network.Resource
import com.net.machinetest.data.repository.NewsRepository
import com.net.machinetest.data.responses.news.AllNewsResponse
import com.net.machinetest.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : BaseViewModel(repository) {

    private val _newsResponse: MutableLiveData<Resource<AllNewsResponse>> = MutableLiveData()
    val newsResponse: LiveData<Resource<AllNewsResponse>>
        get() = _newsResponse

    fun getData( ) = viewModelScope.launch {
        _newsResponse.value = Resource.Loading
        _newsResponse.value = repository.getData()
    }

}