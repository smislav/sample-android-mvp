package com.githubapp.ui.search

import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
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
import javax.inject.Inject


class SearchActivity : BaseActivity(), ISearchView, RepoAdapter.ClickListener {
    companion object {
        const val EXTRA_REPO = "com.githubapp.ui.search.SearchActivity.EXTRA_REPO"
        const val EXTRA_USER = "com.githubapp.ui.search.SearchActivity.EXTRA_USER"
    }

    @Inject
    lateinit var mPresenter: SearchPresenter;

    @Inject
    lateinit var mAdapter: RepoAdapter;

    @BindView(R.id.search)
    lateinit var mSearch: SearchView;

    @BindView(R.id.toggle_stars)
    lateinit var mStars: RadioButton;

    @BindView(R.id.toggle_forks)
    lateinit var mForks: RadioButton;

    @BindView(R.id.toggle_updated)
    lateinit var mUpdated: RadioButton;

    @BindView(R.id.refresh_repositories)
    lateinit var mRefresh: SwipeRefreshLayout;

    @BindView(R.id.recycler_repositories)
    lateinit var mRecycler: RecyclerView;

    @BindView(R.id.group_sort)
    lateinit var mSort: RadioGroup;

    lateinit var mPagination: Pagination

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search)

        ButterKnife.bind(this)

        mPresenter.attachView(this)

        mAdapter.clickListener = this

        val layoutManager = LinearLayoutManager(this, OrientationHelper.VERTICAL, false)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        mPagination = object: Pagination(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                submitQuery(false)
            }
        }

        mRecycler.setAdapter(mAdapter)
        mRecycler.setLayoutManager(layoutManager)
        mRecycler.addItemDecoration(itemDecoration)
        mRecycler.addOnScrollListener(mPagination)

        mRefresh.setOnRefreshListener {
            submitQuery(true)
        }

        mSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                submitQuery(true)

                return true
            }

        })

        mSort.setOnCheckedChangeListener { _, id -> submitQuery(true)}

        mPresenter.loadRepos()
    }

    //region HELPERS
    fun submitQuery(reset: Boolean){
        if(reset)
            mPagination.resetState()

        val page = mPagination.currentPage
        val sort = mSort.indexOfChild(findViewById(mSort.checkedRadioButtonId))
        val query = mSearch.query.toString()

        mPresenter.loadRepos(page, query, sort, reset)
    }
    //endregion

    //region CLICKS
    @OnClick(R.id.button_profile)
    fun onProfileClick(){
        mPresenter.onProfileClick()
    }

    override fun onRepoClick(repo: Repo) {
        mPresenter.onRepoClick(repo)
    }

    override fun onOwnerClick(user: User) {
        mPresenter.onUserClick(user)
    }
    //endregion

    //region ACTIONS
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
        mAdapter.clearItems()
    }

    override fun addRepos(repos: List<Repo>) {
        mAdapter.addItems(repos)
    }

    override fun showLoading() {
        mRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        mRefresh.isRefreshing = false
    }
    //endregion
}
