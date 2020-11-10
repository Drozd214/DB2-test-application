package com.oleksandrkarpiuk.db2test.di.modules.data.remote

import dagger.Module

@Module(
    includes = [
        PrivatBankApiModule::class,
        NbuApiModule::class
    ]
)
object RemoteModule