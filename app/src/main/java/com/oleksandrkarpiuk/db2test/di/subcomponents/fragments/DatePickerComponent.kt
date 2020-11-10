package com.oleksandrkarpiuk.db2test.di.subcomponents.fragments

import com.oleksandrkarpiuk.db2test.ui.main.datepicker.DatePickerContract
import com.oleksandrkarpiuk.db2test.ui.main.datepicker.DatePickerFragment
import com.oleksandrkarpiuk.db2test.ui.main.datepicker.DatePickerPresenter
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [DatePickerComponent.ComponentModule::class])
interface DatePickerComponent {


    fun inject(fragment: DatePickerFragment)


    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance view: DatePickerContract.View): DatePickerComponent
    }


    @Module
    object ComponentModule {

        @Provides
        fun provideDatePickerPresenter(
            view: DatePickerContract.View
        ): DatePickerPresenter {
            return DatePickerPresenter(view)
        }
    }


}