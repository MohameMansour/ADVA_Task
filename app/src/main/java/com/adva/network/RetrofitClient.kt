package com.adva.network

import retrofit2.http.*

interface RetrofitClient {

    @GET(Urls.LIST_IMAGES)
    suspend fun getImageList(): NetworkResponse<com.adva.imageList.model.ImageModel, ErrorModel>

//    https://jsonplaceholder.typicode.com/albums/1/photos

}
