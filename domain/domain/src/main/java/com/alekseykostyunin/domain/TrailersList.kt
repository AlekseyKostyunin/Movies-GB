package com.alekseykostyunin.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailersList(

    @SerializedName("trailers")
    @Expose
    val trailers: List<Trailer>
)
