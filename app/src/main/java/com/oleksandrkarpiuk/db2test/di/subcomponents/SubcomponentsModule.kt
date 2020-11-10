package com.oleksandrkarpiuk.db2test.di.subcomponents

import com.oleksandrkarpiuk.db2test.di.subcomponents.activities.ActivitiesModule
import com.oleksandrkarpiuk.db2test.di.subcomponents.fragments.FragmentsModule
import dagger.Module

@Module(
    includes = [
        ActivitiesModule::class,
        FragmentsModule::class
    ]
)
object SubcomponentsModule