package com.alekseykostyunin.movies_gb

import android.app.Application
import com.alekseykostyunin.movies_gb.di.AppComponent
import com.alekseykostyunin.movies_gb.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent = DaggerAppComponent.factory().create(this)
}