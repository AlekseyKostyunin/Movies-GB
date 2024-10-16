package com.alekseykostyunin.movies_gb.data

import com.alekseykostyunin.movies_gb.domain.details.Review
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("docs")
    @Expose
    val reviews: List<Review>
)
