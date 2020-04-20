package com.kotlin.demoinkotlin.ui.photos

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.kotlin.demoinkotlin.R
import com.kotlin.demoinkotlin.databinding.ActivityPhotosListBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PhotosListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var photoListViewModel: PhotoListViewModel

    private lateinit var binding: ActivityPhotosListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_photos_list)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photos_list)

        // Set toolbar title

        binding.toolbar.toolbar?.title = "Albums"

        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        binding.photoList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)

        photoListViewModel = ViewModelProviders.of(this, viewModelFactory)[PhotoListViewModel::class.java]

        //photoListViewModel.getPhotos()

        photoListViewModel.errorMessage.observe(this, Observer {

            error ->
            if (error != null) {
                showError()
            }
        })

        photoListViewModel.successMessage.observe(this, Observer {

            success ->
            if (success != null) {
                showSuccess()
            }
        })

        binding.photoListViewModel = photoListViewModel

    }

    private fun showSuccess() {

        Toast.makeText(this@PhotosListActivity, "Success", Toast.LENGTH_SHORT).show()
    }

    private fun showError() {

        Toast.makeText(this@PhotosListActivity, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return if (item!!.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
