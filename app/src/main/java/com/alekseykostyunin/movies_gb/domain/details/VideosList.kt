package com.alekseykostyunin.movies_gb.domain.details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideosList(
    @SerializedName("videos")
    @Expose
    val trailersList: TrailersList
)
