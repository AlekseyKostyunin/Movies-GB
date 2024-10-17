package com.alekseykostyunin.movies_gb.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Trailer(

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("url")
    @Expose
    val url: String
)
