package com.oleksandrkarpiuk.db2test.ui.main

import com.oleksandrkarpiuk.db2test.data.remote.nbu.model.NbuCourseByDate
import com.oleksandrkarpiuk.db2test.data.remote.privatBank.model.ExchangeRate
import com.oleksandrkarpiuk.db2test.models.Bank
import java.time.LocalDate

interface MainContract {

    interface View {

        fun setPrivatBankDate(date: String)

        fun setNbuBankDate(date: String)

        fun setPrivatBankCourses(courses: List<ExchangeRate>)

        fun setNbuBankCourses(courses: List<NbuCourseByDate>)

        fun showDatePickerDialog(date: LocalDate, bank: Bank)

        fun setNbuBankItem(previousSelected: Int, currentSelected: Int)

        fun showToast(message: String)

        fun showPrivatBankError()

        fun showNbuBankError()

        fun hidePrivatBankError()

        fun hideNbuBankError()

        fun isOnline(): Boolean
    }


    interface ActionListener {

        fun init()

        fun onPrivatBankDateBtnClicked()

        fun onNbuBankDateBtnClicked()

        fun onDatePicked(date: LocalDate, bank: Bank)

        fun onPrivatBankItemClicked(currencyCode: String)
    }
}