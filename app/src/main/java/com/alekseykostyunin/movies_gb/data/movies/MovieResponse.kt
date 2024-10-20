package com.alekseykostyunin.movies_gb.data.movies

import com.alekseykostyunin.movies_gb.domain.movies.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("docs")
    @Expose
    val movies: List<Movie>
)
