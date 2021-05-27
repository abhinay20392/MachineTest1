package com.net.machinetest.ui.base

import androidx.lifecycle.ViewModel
import com.net.machinetest.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {

}