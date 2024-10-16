package com.alekseykostyunin.movies_gb.domain.movies

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "favourite_movies")
data class Movie (

    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("year")
    @Expose
    val year: Int,

    @SerializedName("poster")
    @Expose
    @Embedded
    val poster: Poster,

    @SerializedName("rating")
    @Expose
    @Embedded
    val rating: Rating

) : Serializable
