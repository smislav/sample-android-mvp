package com.githubapp.ui.repository

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.githubapp.R
import com.githubapp.data.models.Repo
import com.githubapp.ui.base.BaseActivity
import com.githubapp.ui.search.SearchActivity
import com.githubapp.utils.startBrowser
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_repo.*
import java.text.DateFormat
import javax.inject.Inject

class RepoActivity : BaseActivity(), IRepoView {
    @Inject lateinit var presenter: RepoPresenter;

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        presenter.attachView(this)
        presenter.getRepo(
                intent.extras?.getString(SearchActivity.EXTRA_USER)!!,
                intent.extras?.getString(SearchActivity.EXTRA_REPO)!!)

    }

    override fun loadRepo(repo: Repo) {
        Glide   .with(this)
                .load(repo.user.image)
                .apply(RequestOptions.circleCropTransform())
                .into(imageUser)

        textUsername.text = repo.user.username

        buttonView.setOnClickListener{
            startBrowser(applicationContext, repo.url)
        }

        infoName.text = repo.name
        infoDesc.text = repo.description
        infoWatchers.text = repo.watchers.toString()
        infoForks.text = repo.forks.toString()
        infoIssues.text = repo.issues.toString()
        infoDate.text = DateFormat.getDateInstance()
                .format(repo.date)
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
        content.visibility = View.GONE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
        content.visibility = View.VISIBLE
    }
}
