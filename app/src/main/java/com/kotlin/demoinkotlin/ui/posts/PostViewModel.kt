package com.kotlin.demoinkotlin.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.demoinkotlin.model.PostResponse

class PostViewModel : ViewModel() {

    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(postResponse: PostResponse) {
        postTitle.value = postResponse.title
        postBody.value = postResponse.body
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostBody(): MutableLiveData<String> {
        return postBody
    }

    fun getActivityName(): String {

        return "PhotoListActivity"
    }

}