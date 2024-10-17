package com.alekseykostyunin.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(

    @SerializedName("kp")
    @Expose
    val kp: Double?

)  : Serializable
