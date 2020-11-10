package com.oleksandrkarpiuk.db2test.data.remote.nbu.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NbuCourseByDate(
    @SerializedName("r030") val currencyId: Int,
    @SerializedName("txt") val currencyName: String,
    @SerializedName("rate") val currencyCourse: Float,
    @SerializedName("cc") val currencyCode: String,
    @SerializedName("exchangedate") val date: String
): Parcelable {

    var isSelected = false
}