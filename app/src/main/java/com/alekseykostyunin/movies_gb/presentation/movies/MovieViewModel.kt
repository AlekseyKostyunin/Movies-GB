package com.alekseykostyunin.movies_gb.presentation.movies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alekseykostyunin.movies_gb.data.ApiFactory
import com.alekseykostyunin.movies_gb.domain.movies.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private var movies = MutableLiveData<List<Movie>>()
    private var isLoading = MutableLiveData<Boolean>()
    private var compositeDisposable = CompositeDisposable()
    private var page = 1

    fun getMovies() : LiveData<List<Movie>>{
        return movies
    }

    fun getIsLoading() : LiveData<Boolean>{
        return isLoading
    }

    fun refresh() {
        loadMovies()
    }

    fun loadMovies(){
        val loading = isLoading.value
        if((loading != null) && loading){
            return
        }
        val disposable = ApiFactory.apiService.loadMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doAfterTerminate { isLoading.value = false }
            .subscribe({ t ->
                movies.value = t.movies
            }
            ) { t -> Log.d(TAG, "Error: " + t.message) }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    companion object{
        const val TAG = "TEST_MainViewModel"
    }

}