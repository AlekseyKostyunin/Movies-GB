package com.alekseykostyunin.movies_gb

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.alekseykostyunin.domain.Movie
import com.alekseykostyunin.domain.Poster
import com.alekseykostyunin.domain.Rating
import com.alekseykostyunin.movies_gb.databinding.MovieItemBinding
import com.alekseykostyunin.movies_gb.presentation.movie.MoviesAdapter
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.verify


@RunWith(JUnit4::class)
class MoviesAdapterTest {

    @Mock
    lateinit var onMovieClickListener: MoviesAdapter.OnMovieClickListener

    @Test
    fun testOnBindViewHolder() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val adapter = MoviesAdapter(context)
        adapter.onMovieClickListener = onMovieClickListener
        val movie = Movie(1, "Test Movie", "Test Description", 2020, Poster("Test Poster"), Rating(10.0))
        val holder = MoviesAdapter.MovieViewHolder(View(context))
        adapter.movies = listOf(movie)
        adapter.favoriteMovies = listOf(movie)
        adapter.onBindViewHolder(holder, 0)
        verify(onMovieClickListener).onMovieClick(movie)
    }


}