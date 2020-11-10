package com.oleksandrkarpiuk.db2test.di.modules.data

import com.oleksandrkarpiuk.db2test.di.modules.data.remote.RemoteModule
import com.oleksandrkarpiuk.db2test.di.modules.data.repositories.RepositoriesModule
import dagger.Module

@Module(
    includes = [
        RemoteModule::class,
        RepositoriesModule::class
    ]
)
object DataModule {
}