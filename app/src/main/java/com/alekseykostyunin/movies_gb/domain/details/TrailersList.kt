package com.alekseykostyunin.movies_gb.domain.details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailersList(

    @SerializedName("trailers")
    @Expose
    val trailers: List<Trailer>
)
