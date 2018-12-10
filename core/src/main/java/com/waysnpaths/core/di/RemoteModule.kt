package com.waysnpaths.core.di

import com.waysnpaths.core.domain.repository.IssueRepository
import com.waysnpaths.core.remote.RemoteClient
import com.waysnpaths.core.remote.RemoteInterface
import com.waysnpaths.core.remote.issue.RemoteIssueMapper
import com.waysnpaths.core.remote.issue.RemoteIssueRepository
import com.waysnpaths.core.remote.user.RemoteUserMapper
import dagger.Module
import dagger.Provides

@Module
class RemoteModule {

    @Provides
    fun remoteClient() = RemoteClient

    @Provides
    fun remoteInterface(remoteClient: RemoteClient) = remoteClient.remoteInterface

    @Provides
    fun remoteUserMapper() = RemoteUserMapper()

    @Provides
    fun remoteIssueMapper(remoteUserMapper: RemoteUserMapper) = RemoteIssueMapper(remoteUserMapper)

    @Provides
    fun issueRepository(remoteInterface: RemoteInterface, remoteIssueMapper: RemoteIssueMapper): IssueRepository {
        return RemoteIssueRepository(remoteInterface, remoteIssueMapper)
    }

}