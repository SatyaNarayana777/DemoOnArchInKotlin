package com.kotlin.demoinkotlin

import com.kotlin.demoinkotlin.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication() {

/*
    override fun onCreate() {
        super.onCreate()
    }
*/

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder().create(this).build()
    }


}
