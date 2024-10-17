package com.alekseykostyunin.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieTest {

    @Test
    fun testMovieCreation() {
        val movie = Movie(1, "Test Movie", "Test Description", 2020, Poster("Test Poster"), Rating(10.0))
        assert(movie.id == 1)
        assert(movie.name == "Test Movie")
        assert(movie.description == "Test Description")
        assert(movie.year == 2020)
        assert(movie.poster.url == "Test Poster")
        assert(movie.rating.kp == 10.0)
    }
}

