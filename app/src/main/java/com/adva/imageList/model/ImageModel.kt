package com.adva.imageList.model

class ImageModel : ArrayList<ImageModelItem>()

data class ImageModelItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)