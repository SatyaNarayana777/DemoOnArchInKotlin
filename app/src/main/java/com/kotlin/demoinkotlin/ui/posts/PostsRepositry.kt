package com.kotlin.demoinkotlin.ui.posts

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.kotlin.demoinkotlin.model.PostResponse
import com.kotlin.demoinkotlin.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class PostsRepositry @Inject constructor(@Named("Api_1") val apiInterface: ApiInterface) {

    var postDetailsResponseMutableLiveData = MutableLiveData<List<PostResponse>>()

    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    var errorMessage: MutableLiveData<String> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getPostDetails(userId: Int) {

        apiInterface.getPosts(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingVisibility.value = 0 }
                .doOnTerminate { loadingVisibility.value = 1 }
                .subscribe(
                        { response -> postDetailsResponseMutableLiveData.value = response },
                        { error -> errorMessage.value = error.localizedMessage })

    }
}
