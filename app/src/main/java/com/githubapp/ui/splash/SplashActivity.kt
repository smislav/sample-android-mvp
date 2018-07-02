package com.githubapp.ui.splash

import android.os.Bundle
import android.os.Handler
import butterknife.ButterKnife
import com.githubapp.R
import com.githubapp.ui.base.BaseActivity
import com.githubapp.ui.login.LoginActivity
import com.githubapp.ui.search.SearchActivity
import com.githubapp.utils.startActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashActivity : BaseActivity(), ISplashView {
    @Inject
    lateinit var mPresenter: SplashPresenter;

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        ButterKnife.bind(this)

        mPresenter.attachView(this)

        Handler().postDelayed({
            mPresenter.checkLogin()
        },3000)
    }

    override fun navigateToMain() {
        startActivity(this, SearchActivity::class.java, clear = true)
    }

    override fun navigateToLogin() {
        startActivity(this, LoginActivity::class.java, clear = true)
    }
}
