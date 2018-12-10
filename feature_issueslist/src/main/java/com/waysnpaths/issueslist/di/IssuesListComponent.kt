package com.waysnpaths.issueslist.di

import androidx.fragment.app.Fragment
import com.waysnpaths.core.di.CoreComponent
import com.waysnpaths.issueslist.ui.IssuesListFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@IssuesListScope
@Component(modules = [IssuesListModule::class], dependencies = [CoreComponent::class])
interface IssuesListComponent : AndroidInjector<IssuesListFragment> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun fragment(fragment: IssuesListFragment): Builder
        fun plus(component: CoreComponent): Builder
        fun build(): IssuesListComponent
    }

    fun inject(fragment: Fragment)
}