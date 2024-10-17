package com.alekseykostyunin.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideosList(
    @SerializedName("videos")
    @Expose
    val trailersList: TrailersList
)
