package com.example.marleyspoonassignment.rx

import io.reactivex.Scheduler

open class SchedulingStrategyFactory constructor(private val subscribingScheduler: Scheduler,
                                                 private val observingScheduler: Scheduler) {
    fun <T> create(): SchedulingStrategy<T> {
        return SchedulingStrategy(subscribingScheduler, observingScheduler)
    }

    fun createCompletableScheduler(): CompletableSchedulingStrategy {
        return CompletableSchedulingStrategy(subscribingScheduler, observingScheduler)
    }

    fun getSubscribingScheduler(): Scheduler {
        return subscribingScheduler
    }

}
