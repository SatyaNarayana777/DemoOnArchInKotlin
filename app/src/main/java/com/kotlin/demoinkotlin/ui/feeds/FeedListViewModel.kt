package com.kotlin.demoinkotlin.ui.feeds

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import android.view.View
import com.kotlin.demoinkotlin.model.FeedsReponse
import com.kotlin.demoinkotlin.network.ApiInterface
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Named

class FeedListViewModel @Inject constructor(@Named("Api_2") private val apiInterface: ApiInterface) : ViewModel() {

    private var feedsList: LiveData<PagedList<FeedsReponse>>
    private val compositeDisposable = CompositeDisposable()
    //private val pageSize = 10
    private var feedsDataSourceFactory: FeedsDataSourceFactory
    var feedListAdapter: FeedListAdapter = FeedListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private val feedsListObserver : Observer<PagedList<FeedsReponse>>

    init {

        feedsListObserver = Observer {

            feed -> feedListAdapter.submitList(feed)

            loadingVisibility.value = View.GONE }

        loadingVisibility.value = View.VISIBLE

        feedsDataSourceFactory = FeedsDataSourceFactory(apiInterface, compositeDisposable)

        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(10)
                .setEnablePlaceholders(false)
                .build()

        val executor = Executors.newFixedThreadPool(5)

        feedsList = LivePagedListBuilder<Int, FeedsReponse>(feedsDataSourceFactory, config).setFetchExecutor(executor).build()

        feedsList.observeForever(feedsListObserver)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()

        feedsList.removeObserver(feedsListObserver)
    }

}
