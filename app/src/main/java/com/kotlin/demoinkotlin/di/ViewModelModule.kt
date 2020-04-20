package com.kotlin.demoinkotlin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.demoinkotlin.ui.feeds.FeedListViewModel
import com.kotlin.demoinkotlin.ui.photos.PhotoListViewModel
import com.kotlin.demoinkotlin.ui.posts.PostListViewModel
import com.kotlin.demoinkotlin.ui.users.UsersListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    internal abstract fun usersListViewModel(listViewModel: UsersListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    internal abstract fun postsListViewModel(viewModel: PostListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotoListViewModel::class)
    internal abstract fun photoListViewModel(viewModel: PhotoListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedListViewModel::class)
    internal abstract fun feedListViewModel(viewModel: FeedListViewModel): ViewModel

    //Add more ViewModels here
}
