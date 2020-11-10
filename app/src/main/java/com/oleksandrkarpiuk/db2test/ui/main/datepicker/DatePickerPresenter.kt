package com.oleksandrkarpiuk.db2test.ui.main.datepicker

import java.time.LocalDate

class DatePickerPresenter(
    private val view: DatePickerContract.View
): DatePickerContract.ActionListener {


    override fun onCreateDialog(date: LocalDate) = view.setDateToDialog(
        date.year,
        date.monthValue - 1,
        date.dayOfMonth
    )


    override fun onDialogOkButtonClicked(year: Int, month: Int, dayOfMonth: Int) {
        view.sendOkResult(LocalDate.of(year, month, dayOfMonth))
    }


}