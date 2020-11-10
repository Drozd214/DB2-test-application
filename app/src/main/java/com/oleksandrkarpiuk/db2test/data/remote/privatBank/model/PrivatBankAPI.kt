package com.oleksandrkarpiuk.db2test.data.remote.privatBank.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PrivatBankAPI {


    @GET("/p24api/exchange_rates?json")
    fun getPrivatBankCourseByDate(@Query("date") date: String): Call<PrivatBankCourseByDate>
}