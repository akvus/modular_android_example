package com.waysnpaths.issueslist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.waysnpaths.core.CoreApplication
import com.waysnpaths.core.domain.model.Issue
import com.waysnpaths.issuedetails.ui.IssueDetailsFragment
import com.waysnpaths.issueslist.R
import com.waysnpaths.issueslist.di.DaggerIssuesListComponent
import kotlinx.android.synthetic.main.issues_list_fragment.*
import timber.log.Timber
import javax.inject.Inject

class IssuesListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: IssuesListViewModel

    private var issuesAdapter: IssuesAdapter? = null

    private var isLoading: Boolean = false

    private var rootView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerIssuesListComponent
            .builder()
            .fragment(this)
            .plus(CoreApplication.coreComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.issues_list_fragment, container, false)
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(IssuesListViewModel::class.java)
            viewModel.getModel().observe(this, Observer(::render))
            if (savedInstanceState == null) {
                viewModel.onInit()
            } else {
                Timber.d("Orientation change. Data restored from a view model that persisted.")
            }
        } else {
            viewModel.getModel().observe(this, Observer(::render))
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun render(issuesListModel: IssuesListModel) {
        renderIssues(issuesListModel.issues)
        renderStatus(issuesListModel.status)
    }

    private fun renderIssues(issues: List<Issue>) {
        issuesAdapter?.submitList(issues)
    }

    private fun renderStatus(status: Status) {
        when (status) {
            Loading -> {
                swipeRefresh.isRefreshing = true
            }
            Loaded -> {
                stopLoading()
            }
            is Error -> {
                stopLoading()
                status.stringId.getContentIfNotHandled()?.let(::displayError)
            }
        }
    }

    private fun displayError(stringId: Int) {
        Snackbar.make(swipeRefresh, stringId, Snackbar.LENGTH_LONG)
            .setAction(R.string.retry) { viewModel.onRefresh() }
            .show()
    }

    private fun stopLoading() {
        swipeRefresh.isRefreshing = false

        progressBar.visibility = View.GONE
        isLoading = false
    }

    private fun setUpView() {
        issuesAdapter = IssuesAdapter { goToDetails(it) }
        rvIssues.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = issuesAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && !isLoading) {
                        isLoading = true
                        progressBar.visibility = View.VISIBLE
                        viewModel.onNextPage()
                    }
                }
            })
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.onRefresh()
        }

        activity?.title = getString(R.string.app_name)
    }

    private fun goToDetails(issue: Issue) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, IssueDetailsFragment.newInstance(issue))
            ?.addToBackStack(null)
            ?.commit()
    }

    companion object {
        fun newInstance() = IssuesListFragment()
    }
}