package com.adva.imageList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adva.network.ErrorModel
import com.adva.network.NetworkResponse
import com.adva.imageList.model.ImageModel
import kotlinx.coroutines.launch

class ImageListViewModel(app: Application) : AndroidViewModel(app) {

    private var repo = ImageListRepository()
    var showLoading = MutableLiveData<Boolean>()
    var imageLists = MutableLiveData<ImageModel>()
    val error = MutableLiveData<ErrorModel>()


    fun getImageList() {
        showLoading.value = true

        viewModelScope.launch {
            val imageList = repo.getImageList()
            when (imageList) {
                is NetworkResponse.Success -> {
                    imageLists.value = imageList.body
                }
                is NetworkResponse.ApiError -> {
                    error.value = imageList.body
                }
                is NetworkResponse.NetworkError -> {
                }
                is NetworkResponse.UnknownError -> {
                }
            }
            showLoading.value = false
        }
    }

}