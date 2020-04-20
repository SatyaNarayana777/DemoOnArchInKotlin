package com.kotlin.demoinkotlin.ui.feeds

import androidx.paging.DataSource
import com.kotlin.demoinkotlin.model.FeedsReponse
import com.kotlin.demoinkotlin.network.ApiInterface
import io.reactivex.disposables.CompositeDisposable

class FeedsDataSourceFactory(val apiInterface: ApiInterface, private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, FeedsReponse>() {

/*
    private val feedsDataSourceLiveData = MutableLiveData<FeedDataSource>()
*/

    override fun create(): DataSource<Int, FeedsReponse> {
        //feedsDataSourceLiveData.postValue(feedsDataSource)
        return FeedDataSource(apiInterface = apiInterface, compositeDisposable = compositeDisposable)
    }
}
