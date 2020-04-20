package com.kotlin.demoinkotlin.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import android.view.View
import com.kotlin.demoinkotlin.model.PostResponse
import com.kotlin.demoinkotlin.R
import javax.inject.Inject

class PostListViewModel @Inject constructor(private val postsRepositry: PostsRepositry) : ViewModel() {

    //private lateinit var subscription: Disposable

    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    val successMessage: MutableLiveData<Int> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val postListAdapter: PostListAdapter = PostListAdapter()

    private val postDetailsResponseObserver : Observer<List<PostResponse>>
    private val loadingVisibilityObserver : Observer<Int>
    private val errorMessageObserver : Observer<String>

    /*init {

        //isPostsLoaded.value = false
        //loadPosts(userId)

    }*/

    init {

        postDetailsResponseObserver = Observer {

            response ->
            if (response != null) {
                onRetrieveUsersListSuccess(response)
            }
        }

        loadingVisibilityObserver = Observer {

            showVisibility ->
            if (showVisibility == 0) {
                onRetrieveUsersListStart()
            } else if (showVisibility == 1) {
                onRetrieveUsersListFinish()
            }
        }

        errorMessageObserver = Observer {
            onRetrieveUsersListError()
        }

    }

    fun loadPosts(userId: Int) {

        postsRepositry.getPostDetails(userId)

        postsRepositry.postDetailsResponseMutableLiveData.observeForever(postDetailsResponseObserver)

        postsRepositry.loadingVisibility.observeForever(loadingVisibilityObserver)

        postsRepositry.errorMessage.observeForever(errorMessageObserver)

    }

    /*private fun loadPosts() {

        subscription = apiInterface.getPosts(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveUsersListStart() }
                .doOnTerminate { onRetrieveUsersListFinish() }
                .subscribe(
                        { result -> onRetrieveUsersListSuccess(result) },
                        { onRetrieveUsersListError() }
                )
    }
*/
    private fun onRetrieveUsersListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveUsersListFinish() {
        loadingVisibility.value = View.GONE

    }

    private fun onRetrieveUsersListSuccess(response: List<PostResponse>) {
        successMessage.value = R.string.success
        successMessage.value = null
        postListAdapter.updatePostsList(response)

    }

    private fun onRetrieveUsersListError() {
        errorMessage.value = R.string.error
        errorMessage.value = null
    }

    override fun onCleared() {
        super.onCleared()

        postsRepositry.loadingVisibility.removeObserver(loadingVisibilityObserver)
        postsRepositry.errorMessage.removeObserver(errorMessageObserver)
        postsRepositry.postDetailsResponseMutableLiveData.removeObserver(postDetailsResponseObserver)
    }
}