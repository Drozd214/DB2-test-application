package com.oleksandrkarpiuk.db2test.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateFormatter {


    private val privatBankFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    private val nbuFormat = DateTimeFormatter.ofPattern("yyyyMMdd")




    fun getPrivatBankFormat(date: LocalDate): String {
        return date.format(privatBankFormat)
    }


    fun getNbuFormat(date: LocalDate): String {
        return date.format(nbuFormat)
    }


    fun getCommonFormat(date: LocalDate): String {
        return getPrivatBankFormat(date)
    }


}