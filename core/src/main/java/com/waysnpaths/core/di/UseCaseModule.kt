package com.waysnpaths.core.di

import com.waysnpaths.core.domain.repository.IssueRepository
import com.waysnpaths.core.domain.useCase.issue.GetIssuesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getIssuesUseCase(issueRepository: IssueRepository) = GetIssuesUseCase(issueRepository)
}