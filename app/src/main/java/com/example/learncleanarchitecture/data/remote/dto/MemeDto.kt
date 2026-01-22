package com.example.learncleanarchitecture.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MemeDto(
   @SerializedName("box_count")
    val boxCount: Int?,
    val id: String?,
    val name: String?,
    val url: String?,
    val width: Int?,
    val height: Int?,
    val captions: Int?,
)
