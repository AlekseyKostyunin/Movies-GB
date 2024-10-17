package com.alekseykostyunin.movies_gb.presentation.favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.alekseykostyunin.data.MovieDataBase
import com.alekseykostyunin.domain.Movie

class FavouriteMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val movieDao = MovieDataBase.getInstance(application).movieDao()

    fun getMovies(): LiveData<List<Movie>> {
        return movieDao.getAllFavouriteMovies()
    }

}