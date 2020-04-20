package com.kotlin.demoinkotlin.ui.users

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.kotlin.demoinkotlin.R
import com.kotlin.demoinkotlin.databinding.ActivityUsersListBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class UsersListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityUsersListBinding

    private lateinit var listViewModel: UsersListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_users_list)

        // Set toolbar title

        binding.toolbar.toolbar?.title = "Users"

        setSupportActionBar(binding.toolbar.toolbar)

        binding.postList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)

        listViewModel = ViewModelProviders.of(this, viewModelFactory)[UsersListViewModel::class.java]

        listViewModel.errorMessage.observe(this, Observer {

            error ->
            if (error != null) {
                showError()
            }
        })

        listViewModel.successMessage.observe(this, Observer {

            success ->
            if (success != null) {
                showSuccess()
            }
        })

        binding.viewModel = listViewModel

    }

    private fun showSuccess() {

        Toast.makeText(this@UsersListActivity, "Success", Toast.LENGTH_SHORT).show()
    }

    private fun showError() {

        Toast.makeText(this@UsersListActivity, "Error", Toast.LENGTH_SHORT).show()
    }

}


