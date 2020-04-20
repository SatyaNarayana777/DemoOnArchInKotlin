package com.kotlin.demoinkotlin.ui.users

import android.annotation.SuppressLint
import com.kotlin.demoinkotlin.model.UserResponse
import com.kotlin.demoinkotlin.network.ApiInterface
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UsersRepositry @Inject constructor(@Named("Api_1") val apiInterface: ApiInterface) {

    var userDetailsResponseMutableLiveData = MutableLiveData<List<UserResponse>>()

    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    var errorMessage: MutableLiveData<String> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getUserDetails() {

        apiInterface.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingVisibility.value = 0 }
                .doOnTerminate { loadingVisibility.value = 1 }
                .subscribe(
                        { response -> userDetailsResponseMutableLiveData.value = response },
                        { error -> errorMessage.value = error.localizedMessage })

    }
}
