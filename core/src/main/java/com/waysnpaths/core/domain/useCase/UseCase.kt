package com.waysnpaths.core.domain.useCase

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class UseCase<Params, Result>(
    private val subscribeOn: Scheduler = Schedulers.io(),
    private val observeOn: Scheduler = AndroidSchedulers.mainThread()
) where Params: Any {

    protected abstract fun run(params: Params): Observable<Result>

    fun execute(params: Params): Observable<Result> {
        return run(params)
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
    }

    class None : Any()
}