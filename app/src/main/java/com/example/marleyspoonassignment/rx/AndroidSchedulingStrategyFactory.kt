package com.example.marleyspoonassignment.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AndroidSchedulingStrategyFactory(subscribingScheduler: Scheduler) :
        SchedulingStrategyFactory(subscribingScheduler, AndroidSchedulers.mainThread()) {

    companion object {
        fun newThread(): AndroidSchedulingStrategyFactory {
            return AndroidSchedulingStrategyFactory(Schedulers.newThread())
        }

        fun io(): AndroidSchedulingStrategyFactory {
            return AndroidSchedulingStrategyFactory(Schedulers.io())
        }
    }

}
