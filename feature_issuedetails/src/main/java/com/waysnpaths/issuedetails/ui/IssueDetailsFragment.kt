package com.waysnpaths.issuedetails.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.waysnpaths.core.CoreContract
import com.waysnpaths.core.domain.model.Issue
import com.waysnpaths.issuedetails.R
import kotlinx.android.synthetic.main.issue_details_fragment.*
import java.text.SimpleDateFormat
import java.util.*


class IssueDetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.issue_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(getIssue())
    }

    private fun getIssue(): Issue {
        return arguments!!.getParcelable(issueArgument)!!
    }

    @SuppressLint("SetTextI18n")
    private fun setUpView(issue: Issue) {
        tvTitle.text = issue.title.capitalize()
        tvBody.movementMethod = ScrollingMovementMethod()
        tvBody.text = issue.body
        tvInformation.text = tvInformation.context.getString(R.string.author, issue.user.login,
            SimpleDateFormat(CoreContract.dateFormat, Locale.ENGLISH).format(issue.createdAt))
        activity?.title = "#${issue.id} (${issue.state})"
    }

    companion object {
        private const val issueArgument = "issue"

        fun newInstance(issue: Issue) = IssueDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(issueArgument, issue)
            }
        }
    }
}