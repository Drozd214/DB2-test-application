package com.oleksandrkarpiuk.db2test.di.subcomponents.activities

import com.oleksandrkarpiuk.db2test.data.remote.nbu.model.NbuAPI
import com.oleksandrkarpiuk.db2test.data.remote.privatBank.model.PrivatBankAPI
import com.oleksandrkarpiuk.db2test.ui.main.MainActivity
import com.oleksandrkarpiuk.db2test.ui.main.MainContract
import com.oleksandrkarpiuk.db2test.ui.main.MainPresenter
import com.oleksandrkarpiuk.db2test.utils.DateFormatter
import com.oleksandrkarpiuk.db2test.utils.StringProvider
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [MainComponent.ComponentModule::class])
interface MainComponent {


    fun inject(activity: MainActivity)


    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance view: MainContract.View): MainComponent
    }


    @Module
    object ComponentModule {

        @Provides
        fun provideMainPresenter(
            view: MainContract.View,
            nbuApi: NbuAPI,
            privatBankApi: PrivatBankAPI,
            stringProvider: StringProvider,
            dateFormatter: DateFormatter
        ): MainPresenter {
            return MainPresenter(
                view,
                nbuApi,
                privatBankApi,
                stringProvider,
                dateFormatter
            )
        }
    }


}