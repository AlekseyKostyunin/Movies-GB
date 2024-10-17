package com.alekseykostyunin.movies_gb.data

import com.alekseykostyunin.movies_gb.domain.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("docs")
    @Expose
    val movies: List<Movie>
)
