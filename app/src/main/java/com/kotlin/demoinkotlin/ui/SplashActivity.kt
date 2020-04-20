package com.kotlin.demoinkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.demoinkotlin.R
import com.kotlin.demoinkotlin.ui.users.UsersListActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this@SplashActivity, UsersListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
