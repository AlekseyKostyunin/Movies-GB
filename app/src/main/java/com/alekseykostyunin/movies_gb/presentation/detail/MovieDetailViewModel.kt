package com.alekseykostyunin.movies_gb.presentation.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TAG = "TEST_MovieDetailViewModel"
    }

    private var compositeDisposable = CompositeDisposable()
    private var trailers = MutableLiveData<List<com.alekseykostyunin.domain.Trailer>>()
    private var reviews = MutableLiveData<List<com.alekseykostyunin.domain.Review>>()
    private var movieDao = com.alekseykostyunin.data.MovieDataBase.getInstance(application).movieDao()

    fun getFavouriteMovie(id: Int): LiveData<com.alekseykostyunin.domain.Movie> {
        return movieDao.getFavouriteMovie(id)
    }

    fun getTrailers(): LiveData<List<com.alekseykostyunin.domain.Trailer>> {
        return trailers
    }

    fun getReviews(): LiveData<List<com.alekseykostyunin.domain.Review>> {
        return reviews
    }

    fun loadReviews(id: Int) {
        val disposable = com.alekseykostyunin.data.ApiFactory.apiService.loadReviews(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.reviews }
            .subscribe({ t -> reviews.value = t },
                { t -> Log.d(TAG, t.toString()) })
        compositeDisposable.add(disposable)
    }

    fun insertMovie(movie: com.alekseykostyunin.domain.Movie) {
        val disposable = movieDao.insertMovie(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun removeMovie(id: Int) {
        val disposable = movieDao.removeMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun loadTrailers(id: Int) {
        val disposable = com.alekseykostyunin.data.ApiFactory.apiService.loadTrailers(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.videosList[0].trailersList.trailers }
            .subscribe({ t -> //                    trailers.value = t.videosList[0].trailersList.trailers
                trailers.value = t
            },
            { t -> Log.d(TAG, t.toString()) })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}