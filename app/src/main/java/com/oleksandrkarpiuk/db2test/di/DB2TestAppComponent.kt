package com.oleksandrkarpiuk.db2test.di

import android.content.Context
import com.oleksandrkarpiuk.db2test.di.modules.core.CoreModule
import com.oleksandrkarpiuk.db2test.di.modules.data.DataModule
import com.oleksandrkarpiuk.db2test.di.subcomponents.interfaces.ExposeActivitySubcomponents
import com.oleksandrkarpiuk.db2test.di.subcomponents.interfaces.ExposeFragmentSubcomponents
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    CoreModule::class,
    DataModule::class
])
interface DB2TestAppComponent:
    ExposeActivitySubcomponents,
    ExposeFragmentSubcomponents
{


    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): DB2TestAppComponent
    }
}