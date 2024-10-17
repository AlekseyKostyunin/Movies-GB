package com.alekseykostyunin.movies_gb.data

import com.alekseykostyunin.movies_gb.domain.VideosList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("docs")
    @Expose
    val videosList: List<VideosList>
)
