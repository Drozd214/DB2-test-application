package com.oleksandrkarpiuk.db2test.di.modules.data.remote

import com.oleksandrkarpiuk.db2test.data.remote.privatBank.PrivatBankRestAPI.Companion.BASE_URL
import com.oleksandrkarpiuk.db2test.data.remote.privatBank.model.PrivatBankAPI
import com.oleksandrkarpiuk.db2test.di.qualifiers.Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object PrivatBankApiModule {


    @Singleton
    @Api(Api.Type.PRIVAT_BANK)
    @Provides
    fun providePrivatBankRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun providePrivatBankApi(@Api(Api.Type.PRIVAT_BANK) retrofit: Retrofit): PrivatBankAPI {
        return retrofit.create(PrivatBankAPI::class.java)
    }
}