package com.kotlin.demoinkotlin.di

import com.kotlin.demoinkotlin.ui.photos.PhotosListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun providePhotosFragment(): PhotosListFragment
}