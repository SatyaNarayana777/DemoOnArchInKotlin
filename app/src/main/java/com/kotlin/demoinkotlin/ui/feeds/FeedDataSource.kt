package com.kotlin.demoinkotlin.ui.feeds

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.kotlin.demoinkotlin.model.FeedsReponse
import com.kotlin.demoinkotlin.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedDataSource @Inject constructor(val apiInterface: ApiInterface, val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, FeedsReponse>() {

/*
    var feedsResponseMutableLiveData = MutableLiveData<List<FeedsReponse>>()
*/

    var errorMessage: MutableLiveData<String> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, FeedsReponse>) {

        compositeDisposable.add(apiInterface.getFeeds(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    //feedsResponseMutableLiveData.value = result.articles
                    callback.onResult(result.articles, null, 2)
                }, { error -> errorMessage.value = error.localizedMessage }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, FeedsReponse>) {

        compositeDisposable.add(apiInterface.getFeeds(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    //feedsResponseMutableLiveData.value = result.articles
                    callback.onResult(result.articles, params.key + 1)
                }, { error -> errorMessage.value = error.localizedMessage }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, FeedsReponse>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
