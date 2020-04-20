package com.kotlin.demoinkotlin.di

import android.app.Application
import com.kotlin.demoinkotlin.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (ActivitiesBindingModule::class), (NetworkModule::class), (FragmentBindingModule::class)])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: MyApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder

        fun build(): AppComponent
    }
}
