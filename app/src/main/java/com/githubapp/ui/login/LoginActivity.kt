package com.githubapp.ui.login

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.githubapp.R
import com.githubapp.ui.base.BaseActivity
import com.githubapp.ui.search.SearchActivity
import com.githubapp.utils.startActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : BaseActivity(), ILoginView {
    @Inject
    lateinit var mPresenter: LoginPresenter

    @BindView(R.id.edit_email)
    lateinit var mEmail: EditText

    @BindView(R.id.edit_password)
    lateinit var mPassword: EditText

    @BindView(R.id.button_login)
    lateinit var mLogin: Button

    @BindView(R.id.progress)
    lateinit var mProgress: FrameLayout

    @BindView(R.id.content)
    lateinit var mContent: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        ButterKnife.bind(this)

        mPresenter.attachView(this)
    }

    @OnClick(R.id.button_login)
    fun onLoginClick(){
        mPresenter.login(
                mEmail.text.toString(),
                mPassword.text.toString())
    }

    override fun showLoading() {
        mProgress.visibility = View.VISIBLE
        mContent.visibility = View.GONE}


    override fun hideLoading() {
        mProgress.visibility = View.GONE
        mContent.visibility = View.VISIBLE
    }

    override fun loginSucceeded() {
        startActivity(this, SearchActivity::class.java, clear = true)
    }

    override fun loginFailed() {
        Toast.makeText(this, "Wrong username or password.", Toast.LENGTH_LONG).show()
    }
}
