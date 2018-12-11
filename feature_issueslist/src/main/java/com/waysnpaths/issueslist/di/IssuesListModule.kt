package com.waysnpaths.issueslist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.waysnpaths.core.di.ViewModelFactory
import com.waysnpaths.core.di.ViewModelKey
import com.waysnpaths.issueslist.ui.list.IssuesListFragment
import com.waysnpaths.issueslist.ui.list.IssuesListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class IssuesListModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): IssuesListFragment

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(IssuesListViewModel::class)
    internal abstract fun issuesListViewModel(viewModel: IssuesListViewModel): ViewModel

}