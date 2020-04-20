package com.kotlin.demoinkotlin.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import android.view.View
import javax.inject.Inject
import com.kotlin.demoinkotlin.model.UserResponse
import com.kotlin.demoinkotlin.R

class UsersListViewModel @Inject constructor(private val usersRepositry: UsersRepositry) : ViewModel() {

    //private lateinit var subscription: Disposable

    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    val successMessage: MutableLiveData<Int> = MutableLiveData()

    //private var mApiResponseMediatorLiveData: MediatorLiveData<List<UserResponse>> = MediatorLiveData<List<UserResponse>>()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val userListAdapter: UsersListAdapter = UsersListAdapter()

    private var userDetailsResponseObserver : Observer<List<UserResponse>>

    private var loadingVisibilityObserver : Observer<Int>

    private var errorMessageObserver : Observer<String>

    init {
        //loadUsers()

        usersRepositry.getUserDetails()

        userDetailsResponseObserver = Observer{
             response ->
            if (response != null) {
                onRetrieveUsersListSuccess(response)
            }
        }

        usersRepositry.userDetailsResponseMutableLiveData.observeForever(userDetailsResponseObserver)

        loadingVisibilityObserver = Observer {

            showVisibility ->
            if (showVisibility == 0) {
                onRetrieveUsersListStart()
            } else if (showVisibility == 1) {
                onRetrieveUsersListFinish()
            }

        }

        usersRepositry.loadingVisibility.observeForever (loadingVisibilityObserver)

        errorMessageObserver = Observer {

            onRetrieveUsersListError()

        }

        usersRepositry.errorMessage.observeForever (errorMessageObserver)

    }


    /* private fun loadUsers() {

         subscription = apiInterface.getUsers()
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .doOnSubscribe { onRetrieveUsersListStart() }
                 .doOnTerminate { onRetrieveUsersListFinish() }
                 .subscribe(
                         { result -> onRetrieveUsersListSuccess(result) },
                         { onRetrieveUsersListError() }
                 )
     }*/

    private fun onRetrieveUsersListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveUsersListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveUsersListSuccess(response: List<UserResponse>) {
        successMessage.value = R.string.success
        successMessage.value = null
        userListAdapter.updateUsersList(response)
    }

    private fun onRetrieveUsersListError() {

        errorMessage.value = R.string.error
        errorMessage.value = null
    }

    override fun onCleared() {
        super.onCleared()

        usersRepositry.userDetailsResponseMutableLiveData.removeObserver(userDetailsResponseObserver)
        usersRepositry.loadingVisibility.removeObserver(loadingVisibilityObserver)
        usersRepositry.errorMessage.removeObserver(errorMessageObserver)

    }
}
