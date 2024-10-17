package com.alekseykostyunin.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PosterTest {

    @Test
    fun testPosterCreation() {
        val poster = Poster("Test Poster URL")
        assert(poster.url == "Test Poster URL")
    }
}