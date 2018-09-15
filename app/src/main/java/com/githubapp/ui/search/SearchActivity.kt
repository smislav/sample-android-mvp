package com.githubapp.ui.search

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubapp.R
import com.githubapp.data.models.Repo
import com.githubapp.data.models.User
import com.githubapp.helpers.Pagination
import com.githubapp.ui.base.BaseActivity
import com.githubapp.ui.profile.ProfileActivity
import com.githubapp.ui.repository.RepoActivity
import com.githubapp.ui.search.adapters.RepoAdapter
import com.githubapp.ui.user.UserActivity
import com.githubapp.utils.startActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject


class SearchActivity : BaseActivity(), ISearchView, RepoAdapter.ClickListener {
    companion object {
        const val EXTRA_REPO = "com.githubapp.ui.search.SearchActivity.EXTRA_REPO"
        const val EXTRA_USER = "com.githubapp.ui.search.SearchActivity.EXTRA_USER"
    }

    @Inject lateinit var presenter: SearchPresenter;
    @Inject lateinit var adapter: RepoAdapter;

    private lateinit var pagination: Pagination

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        presenter.attachView(this)

        adapter.clickListener = this

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        pagination = object: Pagination(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                submitQuery(false)
            }
        }

        recyclerRepos.adapter = adapter
        recyclerRepos.layoutManager = layoutManager
        recyclerRepos.addItemDecoration(itemDecoration)
        recyclerRepos.addOnScrollListener(pagination)

        buttonProfile.setOnClickListener{ onProfileClick() }

        refreshRepos.setOnRefreshListener { submitQuery(true) }

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                submitQuery(true)

                return true
            }

        })

        groupSort.setOnCheckedChangeListener { _, id -> submitQuery(true)}

        presenter.loadRepos()
    }


    fun submitQuery(reset: Boolean){
        if(reset)
            pagination.resetState()

        val page = pagination.currentPage
        val sort = groupSort.indexOfChild(findViewById(groupSort.checkedRadioButtonId))
        val query = search.query.toString()

        presenter.loadRepos(page, query, sort, reset)
    }

    fun onProfileClick(){
        presenter.onProfileClick()
    }

    override fun onRepoClick(repo: Repo) {
        presenter.onRepoClick(repo)
    }

    override fun onOwnerClick(user: User) {
        presenter.onUserClick(user)
    }

    override fun navigateToProfile() {
        startActivity(this, ProfileActivity::class.java)
    }

    override fun navigateToUser(username: String) {
        val extras = Bundle()
        extras.putString(EXTRA_USER, username)

        startActivity(this, UserActivity::class.java, extras)
    }

    override fun navigateToRepo(username: String, repoName: String) {
        val extras = Bundle()
        extras.putString(EXTRA_REPO, repoName)
        extras.putString(EXTRA_USER, username)

        startActivity(this, RepoActivity::class.java, extras)
    }

    override fun clearRepos() {
        adapter.clearItems()
    }

    override fun addRepos(repos: List<Repo>) {
        adapter.addItems(repos)
    }

    override fun showLoading() {
        refreshRepos.isRefreshing = true
    }

    override fun hideLoading() {
        refreshRepos.isRefreshing = false
    }
}
