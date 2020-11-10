package com.oleksandrkarpiuk.db2test.ui.main.datepicker

import android.os.Bundle
import com.oleksandrkarpiuk.db2test.models.Bank
import java.time.LocalDate

internal object ExtraKeys {

    const val ARG_DATE = "date"
    const val ARG_BANK = "bank"
}


internal data class Extras(
    val date: LocalDate,
    val bank: Bank
)


internal fun extractExtras(args: Bundle) : Extras {
    return Extras(
        date = args.getSerializable(ExtraKeys.ARG_DATE) as LocalDate,
        bank = args.getSerializable(ExtraKeys.ARG_BANK) as Bank
    )
}


fun DatePickerFragment.Companion.newInstance(date: LocalDate, bank: Bank) : DatePickerFragment {
    return DatePickerFragment()
        .apply {
            arguments = Bundle().apply {
                putSerializable(ExtraKeys.ARG_DATE, date)
                putSerializable(ExtraKeys.ARG_BANK, bank)
            }
        }
}