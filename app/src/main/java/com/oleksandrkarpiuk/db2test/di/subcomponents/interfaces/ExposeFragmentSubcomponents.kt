package com.oleksandrkarpiuk.db2test.di.subcomponents.interfaces

import com.oleksandrkarpiuk.db2test.di.subcomponents.fragments.DatePickerComponent

interface ExposeFragmentSubcomponents {

    fun createDatePickerComponent(): DatePickerComponent.Factory
}