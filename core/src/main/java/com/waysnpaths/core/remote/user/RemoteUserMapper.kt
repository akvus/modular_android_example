package com.waysnpaths.core.remote.user

import com.waysnpaths.core.domain.Mapper
import com.waysnpaths.core.domain.model.User

class RemoteUserMapper : Mapper<RemoteUser, User> {
    override fun map(from: RemoteUser): User {
        return User(from.login)
    }
}