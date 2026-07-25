package com.ceej.expensetracker

import android.app.Application
import di.domainModule
import com.ceej.common.expensetracker.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ExpenseTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ExpenseTrackerApplication)

            modules(
                dataModule,
                domainModule
            )
        }
    }
}