package com.oleksandrkarpiuk.db2test.utils

import android.content.Context
import androidx.annotation.StringRes

class StringProvider(private val context: Context) {


    fun getString(@StringRes id: Int, vararg args: Any): String {
        return context.getString(id, *args)
    }


}