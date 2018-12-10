package com.waysnpaths.exercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.waysnpaths.issueslist.ui.IssuesListFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setUpView()
        } else {
            Timber.d("On orientation change not setting up view again.")
        }
    }

    private fun setUpView() {
        goTo(IssuesListFragment.newInstance())
    }

    private fun goTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
