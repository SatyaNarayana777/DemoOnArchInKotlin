package com.kotlin.demoinkotlin.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import android.content.Intent
import androidx.databinding.BindingAdapter
import android.graphics.Color
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.kotlin.demoinkotlin.ui.feeds.FeedsListActivity
import com.kotlin.demoinkotlin.ui.photos.PhotosListActivity
import com.kotlin.demoinkotlin.ui.posts.PostsListActivity
import com.kotlin.demoinkotlin.utils.extension.getParentActivity
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("android:profileImage")
fun setImage(view: ImageView, imageUrl: String) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()

    if (parentActivity != null ) {

        val circularProgressDrawable = androidx.swiperefreshlayout.widget.CircularProgressDrawable(parentActivity)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.backgroundColor = Color.TRANSPARENT
        circularProgressDrawable.colorSchemeColors
        circularProgressDrawable.start()

        val requestOptions = RequestOptions()
        requestOptions.placeholder(circularProgressDrawable)
        //requestOptions.error(R.drawable.ic_error)

        //Glide.with(view.context).load(imageUrl).into(view)
        Glide.with(view.context)
                .load(imageUrl)
                .apply(requestOptions)
                //.placeholder(circularProgressDrawable)
                .into(view)

    }
}

@BindingAdapter("adapter")
fun setAdapter(view: androidx.recyclerview.widget.RecyclerView, adapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("onClickUser")
fun setOnclickUser(view: View, userId: Int) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    view.setOnClickListener {

        if (parentActivity != null) {

            val intent = Intent(parentActivity, PostsListActivity::class.java)
            intent.putExtra("USER_ID", userId)

            parentActivity.startActivity(intent)
        }
    }
}

@BindingAdapter("onClickPost")
fun setOnclickPost(view: View, activityName: String) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    view.setOnClickListener {

        if (parentActivity != null) {
            val intent = Intent(parentActivity, PhotosListActivity::class.java)

            parentActivity.startActivity(intent)
        }
    }
}

@BindingAdapter("onClickPhoto")
fun setOnclickPhoto(view: View, photoUrl: String) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    view.setOnClickListener {

        if (parentActivity != null) {
        val intent = Intent(parentActivity, FeedsListActivity::class.java)

            parentActivity.startActivity(intent)
        }
        //Toast.makeText(parentActivity,photoUrl,Toast.LENGTH_SHORT).show()
    }
}

@BindingAdapter("onClickFeed")
fun setOnclickFeed(view: View, authorName: String) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    view.setOnClickListener {

        Toast.makeText(parentActivity, authorName, Toast.LENGTH_SHORT).show()
    }
}
