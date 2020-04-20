package com.kotlin.demoinkotlin.ui.photos

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.kotlin.demoinkotlin.model.PhotosResponse
import com.kotlin.demoinkotlin.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class PhotosRepositry @Inject constructor(@Named("Api_1") val apiInterface: ApiInterface) {

    var photosResponseMutableLivaDeata = MutableLiveData<List<PhotosResponse>>()

    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    var errorMessage: MutableLiveData<String> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getPhotoDetails() {

        apiInterface.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingVisibility.value = 0 }
                .doOnTerminate { loadingVisibility.value = 1 }
                .subscribe(
                        { response -> photosResponseMutableLivaDeata.value = response },
                        { error -> errorMessage.value = error.localizedMessage })
    }
}
