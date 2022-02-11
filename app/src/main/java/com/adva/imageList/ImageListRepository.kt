package com.adva.imageList

import com.adva.network.RetrofitBuilder

class ImageListRepository {

    val client = RetrofitBuilder.instant

//    suspend fun getMyPopularPerson() = client.getPopularPerson("3fb43d674c421dab1ba9b705300cea01" , "en-US" , 1)

    suspend fun getImageList() = client.getImageList()
}