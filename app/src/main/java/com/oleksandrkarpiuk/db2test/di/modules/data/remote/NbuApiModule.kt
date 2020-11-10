package com.oleksandrkarpiuk.db2test.di.modules.data.remote

import com.oleksandrkarpiuk.db2test.data.remote.nbu.NbuRestAPI.Companion.BASE_URL
import com.oleksandrkarpiuk.db2test.data.remote.nbu.model.NbuAPI
import com.oleksandrkarpiuk.db2test.di.qualifiers.Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NbuApiModule {


    @Singleton
    @Api(Api.Type.NBU)
    @Provides
    fun provideNbuRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideNbuAPI(@Api(Api.Type.NBU) retrofit: Retrofit): NbuAPI {
        return retrofit.create(NbuAPI::class.java)
    }
}