package com.oleksandrkarpiuk.db2test.di.subcomponents.interfaces

import com.oleksandrkarpiuk.db2test.di.subcomponents.activities.MainComponent

interface ExposeActivitySubcomponents {

    fun createMainComponent(): MainComponent.Factory
}