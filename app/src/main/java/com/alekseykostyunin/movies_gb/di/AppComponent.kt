package com.alekseykostyunin.movies_gb.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}