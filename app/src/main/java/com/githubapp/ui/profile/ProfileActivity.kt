package com.githubapp.ui.profile

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.githubapp.R
import com.githubapp.data.models.User
import com.githubapp.ui.base.BaseActivity
import com.githubapp.ui.splash.SplashActivity
import com.githubapp.utils.startActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class ProfileActivity : BaseActivity(), IProfileView {
    @Inject
    lateinit var mPresenter: ProfilePresenter

    @BindView(R.id.image_user)
    lateinit var mAvatar: ImageView

    @BindView(R.id.text_username)
    lateinit var mTitle: TextView

    @BindView(R.id.button_logout)
    lateinit var mView: Button

    @BindView(R.id.info_name)
    lateinit var mName: TextView

    @BindView(R.id.info_bio)
    lateinit var mBio: TextView

    @BindView(R.id.info_email)
    lateinit var mEmail: TextView

    @BindView(R.id.info_location)
    lateinit var mLocation: TextView

    @BindView(R.id.info_company)
    lateinit var mCompany: TextView

    @BindView(R.id.info_username)
    lateinit var mUsername: TextView

    @BindView(R.id.info_type)
    lateinit var mType: TextView

    @BindView(R.id.progress)
    lateinit var mProgress: FrameLayout

    @BindView(R.id.content)
    lateinit var mContent: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)

        ButterKnife.bind(this)

        mPresenter.attachView(this)
        mPresenter.getAuthenticatedUser()
    }

    override fun loadUser(user: User) {
        Glide   .with(applicationContext)
                .load(user.image)
                .apply(RequestOptions.circleCropTransform())
                .into(mAvatar)

        mTitle.text = user.username

        mView.setOnClickListener{
           mPresenter.logout()
        }

        mName.text = user.name
        mBio.text = user.bio
        mEmail.text = user.email
        mLocation.text = user.location
        mCompany.text = user.company
        mUsername.text = user.username
        mType.text = user.type
    }

    override fun navigateToSplash() {
        startActivity(this, SplashActivity::class.java, clear = true)
    }

    override fun showLoading() {
        mProgress.visibility = View.VISIBLE
        mContent.visibility = View.GONE
    }

    override fun hideLoading() {
        mProgress.visibility = View.GONE
        mContent.visibility = View.VISIBLE
    }
}
