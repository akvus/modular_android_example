package com.waysnpaths.core.util.extension

import com.waysnpaths.core.remote.RemoteClient
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CompositeDisposableKtTest {

    @Test
    fun plusAssign() {
        val disposables = CompositeDisposable()
        val disposable = Observable.just(1).subscribe()

        disposables += disposable

        assertThat(disposables.size(), equalTo(1))
    }
}