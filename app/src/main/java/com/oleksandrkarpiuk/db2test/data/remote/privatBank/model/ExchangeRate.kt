package com.oleksandrkarpiuk.db2test.data.remote.privatBank.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExchangeRate(
    @SerializedName("baseCurrency") val baseCurrencyCode: String,
    @SerializedName("currency") val currencyCode: String,
    @SerializedName("saleRateNB") val saleRateNbu: Float,
    @SerializedName("purchaseRateNB") val purchaseRateNbu: Float,
    @SerializedName("saleRate") val saleRate: Float?,
    @SerializedName("purchaseRate") val purchaseRate: Float?
): Parcelable