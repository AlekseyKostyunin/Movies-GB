package com.alekseykostyunin.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1.4/movie?token=VRY7JS6-4TB4C8P-MR49NYR-PDYB8KE&rating.kp=7-10&sortField=votes.kp&sortType=-1&notNullFields=poster.url&limit=30&page=1")
    fun loadMovies(): Single<MovieResponse>

    @GET("v1.4/movie?token=VRY7JS6-4TB4C8P-MR49NYR-PDYB8KE&page=1&limit=1&selectFields=videos")
    fun loadTrailers(
        @Query("id")
        id: Int
    ): Single<TrailerResponse>

    @GET("v1.4/review?token=VRY7JS6-4TB4C8P-MR49NYR-PDYB8KE&page=1&limit=10&selectFields=type&selectFields=review&selectFields=author")
    fun loadReviews(
        @Query("movieId")
        movieId: Int
    ): Single<ReviewResponse>
}