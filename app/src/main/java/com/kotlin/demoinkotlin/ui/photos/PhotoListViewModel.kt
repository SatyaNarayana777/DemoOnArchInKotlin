package com.kotlin.demoinkotlin.ui.photos

import androidx.lifecycle.*
import android.view.View
import com.kotlin.demoinkotlin.model.PhotosResponse
import com.kotlin.demoinkotlin.R
import javax.inject.Inject

class PhotoListViewModel @Inject constructor(private val photosRepositry: PhotosRepositry) : ViewModel() {

    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    val successMessage: MutableLiveData<Int> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val photoListAdapter: PhotosListAdapter = PhotosListAdapter()

    private val photosResponseObserver : Observer<List<PhotosResponse>>

    private val loadingVisibilityObserver : Observer<Int>

    private val errorMessageObserver : Observer<String>

    init {

        photosResponseObserver = Observer {
            response -> if (response != null) {
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

        getPhotos()
    }

    private fun getPhotos() {

        photosRepositry.getPhotoDetails()

        photosRepositry.photosResponseMutableLivaDeata.observeForever(photosResponseObserver)

        photosRepositry.loadingVisibility.observeForever(loadingVisibilityObserver)

        photosRepositry.errorMessage.observeForever(errorMessageObserver)

    }

    private fun onRetrieveUsersListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveUsersListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveUsersListSuccess(response: List<PhotosResponse>) {
        successMessage.value = R.string.success
        successMessage.value = null
        photoListAdapter.updatePhotosList(response)
    }

    private fun onRetrieveUsersListError() {

        errorMessage.value = R.string.error
        errorMessage.value = null
    }

    override fun onCleared() {
        super.onCleared()

        photosRepositry.photosResponseMutableLivaDeata.removeObserver(photosResponseObserver)

        photosRepositry.loadingVisibility.removeObserver(loadingVisibilityObserver)

        photosRepositry.errorMessage.removeObserver(errorMessageObserver)

    }
}
