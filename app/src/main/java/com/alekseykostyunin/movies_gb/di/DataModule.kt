package com.alekseykostyunin.movies_gb.di

import android.app.Application
import com.alekseykostyunin.movies_gb.data.favourite.MovieDao
import com.alekseykostyunin.movies_gb.data.favourite.MovieDataBase
import com.alekseykostyunin.movies_gb.data.movies.ApiFactory
import com.alekseykostyunin.movies_gb.data.movies.ApiService
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object DataModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(application: Application): MovieDataBase {
        return MovieDataBase.getInstance(application)
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDataBase): MovieDao {
        return movieDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(ApiFactory.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}