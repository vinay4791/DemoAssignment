package com.example.marleyspoonassignment.rx

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.CompletableTransformer
import io.reactivex.Scheduler

class CompletableSchedulingStrategy constructor(private val subscribingScheduler: Scheduler,
                                                private val observingScheduler: Scheduler) : CompletableTransformer {
    override fun apply(upstream: Completable): CompletableSource {
        return upstream
                .subscribeOn(subscribingScheduler)
                .observeOn(observingScheduler)
    }
}
