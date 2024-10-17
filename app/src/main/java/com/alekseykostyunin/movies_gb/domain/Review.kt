package com.alekseykostyunin.movies_gb.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Review(

    @SerializedName("author")
    @Expose
    val author: String,

    @SerializedName("review")
    @Expose
    val review: String,

    @SerializedName("type")
    @Expose
    val type: String
)
