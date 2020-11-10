package com.oleksandrkarpiuk.db2test.data.remote.nbu.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NbuAPI {


    @GET("/NBUStatService/v1/statdirectory/exchange?&json")
    fun getNbuCoursesOfDate(@Query("date") date: String): Call<List<NbuCourseByDate>>


}