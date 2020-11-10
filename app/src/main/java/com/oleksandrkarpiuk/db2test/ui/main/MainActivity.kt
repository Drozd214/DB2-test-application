package com.oleksandrkarpiuk.db2test.ui.main

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.db2test.DB2TestApplication
import com.oleksandrkarpiuk.db2test.R
import com.oleksandrkarpiuk.db2test.data.remote.nbu.model.NbuCourseByDate
import com.oleksandrkarpiuk.db2test.data.remote.privatBank.model.ExchangeRate
import com.oleksandrkarpiuk.db2test.models.Bank
import com.oleksandrkarpiuk.db2test.ui.main.datepicker.DatePickerFragment
import com.oleksandrkarpiuk.db2test.ui.main.datepicker.newInstance
import com.oleksandrkarpiuk.db2test.utils.StringProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import javax.inject.Inject

const val DIALOG_DATE = "date"

class MainActivity:
    AppCompatActivity(R.layout.activity_main),
    MainContract.View,
    DatePickerFragment.OnDatePickerListener
{


    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var stringProvider: StringProvider
    lateinit var privatBankAdapter: PrivatBankCourseAdapter
    lateinit var nbuBankAdapter: NbuBankCourseAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as DB2TestApplication).getAppComponent()
            .createMainComponent()
            .create(this)
            .inject(this)
        super.onCreate(savedInstanceState)

        init()
    }


    private fun init() {
        initViews()
        presenter.init()
    }


    private fun initViews() {
        initPrivatBankRecycleView()
        initNbuBankRecycleView()

        initDateButtons()
    }


    private fun initPrivatBankRecycleView() = with(privatBankRv) {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = PrivatBankCourseAdapter(listOf()).apply {
            onItemClickedListener = { currencyCode ->
                presenter.onPrivatBankItemClicked(currencyCode)
            }
        }.also {
            privatBankAdapter = it
        }

    }


    private fun initNbuBankRecycleView() = with(nbuBankRv) {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = NbuBankCourseAdapter(listOf()).also {
            nbuBankAdapter = it
        }
    }


    private fun initDateButtons() {

        privatBankDateTv.setOnClickListener {
            presenter.onPrivatBankDateBtnClicked()
        }

        nbuBankDateTv.setOnClickListener {
            presenter.onNbuBankDateBtnClicked()
        }
    }


    override fun setPrivatBankDate(date: String) {
        privatBankDateTv.text = stringProvider.getString(R.string.date, date)
    }


    override fun setNbuBankDate(date: String) {
        nbuBankDateTv.text = stringProvider.getString(R.string.date, date)
    }


    override fun setPrivatBankCourses(courses: List<ExchangeRate>) {
        privatBankAdapter.courses = courses
        privatBankAdapter.notifyDataSetChanged()
    }


    override fun setNbuBankCourses(courses: List<NbuCourseByDate>) {
        nbuBankAdapter.courses = courses
        nbuBankAdapter.notifyDataSetChanged()
    }


    override fun showDatePickerDialog(date: LocalDate, bank: Bank) {
        val dialog = DatePickerFragment.newInstance(date, bank)
        dialog.show(supportFragmentManager, DIALOG_DATE)
    }


    override fun onDatePicked(date: LocalDate, bank: Bank) {
        presenter.onDatePicked(date, bank)
    }


    override fun setNbuBankItem(previousSelected: Int, currentSelected: Int) {
        with(nbuBankAdapter) {
            courses[previousSelected].isSelected = false
            courses[currentSelected].isSelected = true

            notifyItemChanged(previousSelected)
            notifyItemChanged(currentSelected)
        }
        nbuBankRv.scrollToPosition(currentSelected)
    }


    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun showPrivatBankError() {
        privatBankErrorTv.visibility = View.VISIBLE
    }


    override fun showNbuBankError() {
        nbuBankErrorTv.visibility = View.VISIBLE
    }


    override fun hidePrivatBankError() {
        privatBankErrorTv.visibility = View.GONE
    }


    override fun hideNbuBankError() {
        nbuBankErrorTv.visibility = View.GONE
    }


    override fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }


}