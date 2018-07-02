package com.githubapp.ui.user

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.view.View
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.githubapp.R
import com.githubapp.data.models.User
import com.githubapp.ui.base.BaseActivity
import com.githubapp.ui.search.SearchActivity
import com.githubapp.utils.startBrowser
import dagger.android.AndroidInjection
import javax.inject.Inject

class UserActivity : BaseActivity(), IUserView {
    @Inject
    lateinit var mPresenter: UserPresenter

    @BindView(R.id.image_user)
    lateinit var mAvatar: ImageView

    @BindView(R.id.text_username)
    lateinit var mTitle: TextView

    @BindView(R.id.button_view)
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

        setContentView(R.layout.activity_user)

        ButterKnife.bind(this)

        mPresenter.attachView(this)
        mPresenter.getUser(intent.extras.getString(SearchActivity.EXTRA_USER))
    }

    override fun loadUser(user: User) {
        Glide   .with(applicationContext)
                .load(user.image)
                .apply(RequestOptions.circleCropTransform())
                .into(mAvatar)

        mTitle.text = user.username

        mView.setOnClickListener{
            startBrowser(applicationContext, user.url)
        }

        mName.text = user.name
        mBio.text = user.bio
        mEmail.text = user.email
        mLocation.text = user.location
        mCompany.text = user.company
        mUsername.text = user.username
        mType.text = user.type
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
