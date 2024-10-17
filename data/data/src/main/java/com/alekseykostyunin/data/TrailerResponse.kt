package com.alekseykostyunin.data

import com.alekseykostyunin.domain.VideosList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("docs")
    @Expose
    val videosList: List<VideosList>
)
