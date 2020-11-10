package com.oleksandrkarpiuk.db2test

import android.app.Application
import com.oleksandrkarpiuk.db2test.di.DB2TestAppComponent
import com.oleksandrkarpiuk.db2test.di.DaggerDB2TestAppComponent

class DB2TestApplication: Application() {


    private lateinit var appComponent: DB2TestAppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerDB2TestAppComponent.factory().create(applicationContext)
    }


    fun getAppComponent(): DB2TestAppComponent {
        return appComponent
    }
}