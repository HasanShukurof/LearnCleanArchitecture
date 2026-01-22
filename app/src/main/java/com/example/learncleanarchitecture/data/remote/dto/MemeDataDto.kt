package com.example.learncleanarchitecture.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MemeDataDto(
    @SerializedName("memes")
    val memeList: List<MemeDto>
)
