package com.githubapp.ui.repository

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.view.View
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.githubapp.R
import com.githubapp.data.models.Repo
import com.githubapp.ui.base.BaseActivity
import com.githubapp.ui.search.SearchActivity
import com.githubapp.utils.startBrowser
import dagger.android.AndroidInjection
import java.text.DateFormat
import javax.inject.Inject

class RepoActivity : BaseActivity(), IRepoView {
    @Inject
    lateinit var mPresenter: RepoPresenter;

    @BindView(R.id.image_user)
    lateinit var mAvatar: ImageView

    @BindView(R.id.text_username)
    lateinit var mTitle: TextView

    @BindView(R.id.button_view)
    lateinit var mView: Button

    @BindView(R.id.info_name)
    lateinit var mName: TextView

    @BindView(R.id.info_desc)
    lateinit var mDescription: TextView

    @BindView(R.id.info_watchers)
    lateinit var mWatchers: TextView

    @BindView(R.id.info_forks)
    lateinit var mForks: TextView

    @BindView(R.id.info_issues)
    lateinit var mIssues: TextView

    @BindView(R.id.info_date)
    lateinit var mDate: TextView

    @BindView(R.id.progress)
    lateinit var mProgress: FrameLayout

    @BindView(R.id.content)
    lateinit var mContent: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repo)

        ButterKnife.bind(this)

        mPresenter.attachView(this)
        mPresenter.getRepo(
                intent.extras.getString(SearchActivity.EXTRA_USER),
                intent.extras.getString(SearchActivity.EXTRA_REPO))

    }

    override fun loadRepo(repo: Repo) {
        Glide   .with(applicationContext)
                .load(repo.user.image)
                .apply(RequestOptions.circleCropTransform())
                .into(mAvatar)

        mTitle.text = repo.user.username

        mView.setOnClickListener{
            startBrowser(applicationContext, repo.url)
        }

        mName.text = repo.name
        mDescription.text = repo.description
        mWatchers.text = repo.watchers.toString()
        mForks.text = repo.forks.toString()
        mIssues.text = repo.issues.toString()
        mDate.text = DateFormat.getDateInstance()
                .format(repo.date)
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
