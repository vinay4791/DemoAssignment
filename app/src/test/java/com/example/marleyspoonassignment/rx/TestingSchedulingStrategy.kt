package com.example.marleyspoonassignment.rx

import com.example.marleyspoonassignment.rx.SchedulingStrategyFactory
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

open class TestSchedulingStrategyFactory(subscribingScheduler: Scheduler, observingScheduler: Scheduler) :
    SchedulingStrategyFactory(subscribingScheduler, observingScheduler) {
    companion object {
        fun immediate(): TestSchedulingStrategyFactory {
            return TestSchedulingStrategyFactory(Schedulers.trampoline(), Schedulers.trampoline())
        }
    }
}