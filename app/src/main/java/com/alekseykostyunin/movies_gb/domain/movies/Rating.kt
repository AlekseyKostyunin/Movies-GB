package com.alekseykostyunin.movies_gb.domain.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(

    @SerializedName("kp")
    @Expose
    val kp: Double?

)  : Serializable
