package com.githubapp.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.githubapp.ui.login.LoginManager
import com.githubapp.ui.splash.SplashActivity
import com.githubapp.utils.startActivity
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), IBaseView {
    @Inject
    lateinit var mLoginManager: LoginManager

    override fun errorNetwork() {
        Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
    }

    override fun errorUnknown() {
        Toast.makeText(this, "Error connecting server.", Toast.LENGTH_LONG).show()
    }

    override fun errorAccess() {
        mLoginManager.logout()
        startActivity(this, SplashActivity::class.java, clear = true)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

}