package com.alekseykostyunin.data

import com.alekseykostyunin.domain.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("docs")
    @Expose
    val movies: List<Movie>
)
