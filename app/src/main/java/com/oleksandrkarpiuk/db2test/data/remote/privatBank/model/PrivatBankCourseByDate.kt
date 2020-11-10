package com.oleksandrkarpiuk.db2test.data.remote.privatBank.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class PrivatBankCourseByDate(
    @SerializedName("date") val date: String,
    @SerializedName("bank") val bank: String,
    @SerializedName("baseCurrency") val baseCurrencyId: Int,
    @SerializedName("baseCurrencyLit") val baseCurrencyCode: String,
    @SerializedName("exchangeRate") val exchangeRate: List<ExchangeRate>
): Parcelable