package com.alekseykostyunin.movies_gb.data.movies

import com.alekseykostyunin.movies_gb.domain.details.VideosList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("docs")
    @Expose
    val videosList: List<VideosList>
)
