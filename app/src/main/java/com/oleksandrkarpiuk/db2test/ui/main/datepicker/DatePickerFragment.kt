package com.oleksandrkarpiuk.db2test.ui.main.datepicker

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.oleksandrkarpiuk.db2test.DB2TestApplication
import com.oleksandrkarpiuk.db2test.R
import com.oleksandrkarpiuk.db2test.models.Bank
import kotlinx.android.synthetic.main.fragment_date_picker.view.*
import java.time.LocalDate
import javax.inject.Inject

class DatePickerFragment:
    DialogFragment(),
    DatePickerContract.View
{


    companion object;


    @Inject
    lateinit var presenter: DatePickerPresenter

    private lateinit var dateDialog: View
    private lateinit var callback: OnDatePickerListener




    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is OnDatePickerListener) {
            callback = context
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        (requireContext().applicationContext as DB2TestApplication).getAppComponent()
            .createDatePickerComponent()
            .create(this)
            .inject(this)

        presenter.onCreateDialog(extractExtras(requireArguments()).date)

        return AlertDialog.Builder(requireContext())
            .setView(dateDialog)
            .setTitle(R.string.date_picker_title)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                presenter.onDialogOkButtonClicked(
                    dateDialog.date_picker.year,
                    dateDialog.date_picker.month + 1,
                    dateDialog.date_picker.dayOfMonth
                )
            }
            .create()
    }


    override fun setDateToDialog(year: Int, month: Int, day: Int) {
        dateDialog = LayoutInflater.from(requireActivity()).inflate(R.layout.fragment_date_picker, null)
        dateDialog.date_picker.init(year, month, day, null)
    }


    override fun sendOkResult(date: LocalDate) {
        callback.onDatePicked(date, extractExtras(requireArguments()).bank)
    }




    interface OnDatePickerListener {

        fun onDatePicked(date: LocalDate, type: Bank)
    }


}