package com.kotlin.demoinkotlin.ui.feeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.demoinkotlin.model.FeedsReponse

class FeedViewModel : ViewModel() {

    private val feedTitle = MutableLiveData<String>()
    private val feedDescription = MutableLiveData<String>()
    private val feedAuthor = MutableLiveData<String>()
    private val feedImageUrl = MutableLiveData<String>()

    fun bind(feedsResponse: FeedsReponse) {
        feedTitle.value = feedsResponse.title
        feedDescription.value = feedsResponse.description
        feedAuthor.value = feedsResponse.author
        if (feedsResponse.urlToImage!=null) {
            feedImageUrl.value = feedsResponse.urlToImage
        }else{
            feedImageUrl.value = "https://support.appsflyer.com/hc/article_attachments/115011109089/android.png"
        }
    }

    fun getFeedTitle(): MutableLiveData<String> {
        return feedTitle
    }

    fun getFeedDescription(): MutableLiveData<String> {
        return feedDescription
    }

    fun getFeedAuthor(): MutableLiveData<String> {
        return feedTitle
    }

    fun getFeedImageUrl(): MutableLiveData<String> {
        return feedImageUrl
    }

}