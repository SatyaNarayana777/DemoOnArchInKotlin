package com.kotlin.demoinkotlin.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.demoinkotlin.model.UserResponse

class UserViewModel : ViewModel() {

    private val userName = MutableLiveData<String>()
    private val userPhoneNumber = MutableLiveData<String>()
    private val userId = MutableLiveData<Int>()

    fun bind(userResponse: UserResponse) {
        userName.value = userResponse.name
        userPhoneNumber.value = userResponse.phone
        userId.value = userResponse.id
    }

    fun getUserName(): MutableLiveData<String> {
        return userName
    }

    fun getUserPhoneNumber(): MutableLiveData<String> {
        return userPhoneNumber
    }

    fun getUserId(): MutableLiveData<Int> {

        return userId
    }

}
