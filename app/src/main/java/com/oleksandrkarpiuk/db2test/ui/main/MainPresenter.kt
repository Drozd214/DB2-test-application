package com.oleksandrkarpiuk.db2test.ui.main

import com.oleksandrkarpiuk.db2test.R
import com.oleksandrkarpiuk.db2test.data.remote.nbu.model.NbuAPI
import com.oleksandrkarpiuk.db2test.data.remote.nbu.model.NbuCourseByDate
import com.oleksandrkarpiuk.db2test.data.remote.privatBank.model.ExchangeRate
import com.oleksandrkarpiuk.db2test.data.remote.privatBank.model.PrivatBankAPI
import com.oleksandrkarpiuk.db2test.data.remote.privatBank.model.PrivatBankCourseByDate
import com.oleksandrkarpiuk.db2test.models.Bank
import com.oleksandrkarpiuk.db2test.utils.DateFormatter
import com.oleksandrkarpiuk.db2test.utils.StringProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class MainPresenter(
    private val view: MainContract.View,
    private val nbuApi: NbuAPI,
    private val privatBankApi: PrivatBankAPI,
    private val stringProvider: StringProvider,
    private val dateFormatter: DateFormatter
): MainContract.ActionListener {


    private var privatBankCourses = listOf<ExchangeRate>()
    private var nbuBankCourses = listOf<NbuCourseByDate>()
    private lateinit var privatBankDate: LocalDate
    private lateinit var nbuBankDate: LocalDate
    private var isRestored = false

    private var previousSelected: Int = 0




    override fun init() {
        val date = LocalDate.now()
        privatBankDate = date
        nbuBankDate = date

        getPrivatBankCourses(privatBankDate)
        getNbuBankCourses(nbuBankDate)

        with(view) {
            setPrivatBankDate(dateFormatter.getCommonFormat(privatBankDate))
            setNbuBankDate(dateFormatter.getCommonFormat(nbuBankDate))
        }
    }


    private fun getPrivatBankCourses(date: LocalDate) {
        if(!view.isOnline()) {
            view.showToast(stringProvider.getString(R.string.no_connection_error))
            return
        }

        privatBankApi
            .getPrivatBankCourseByDate(dateFormatter.getPrivatBankFormat(date))
            .enqueue(object : Callback<PrivatBankCourseByDate> {

                override fun onFailure(call: Call<PrivatBankCourseByDate>, t: Throwable) {
                    view.showToast(stringProvider.getString(R.string.default_error))
                }

                override fun onResponse(
                    call: Call<PrivatBankCourseByDate>,
                    response: Response<PrivatBankCourseByDate>
                ) {
                    val unformattedCourses = response.body()!!.exchangeRate
                    if (unformattedCourses.isNotEmpty()) {
                        val formattedCourses = mutableListOf<ExchangeRate>()

                        for (course in unformattedCourses) {
                            if (course.saleRate != null) {
                                formattedCourses.add(course)
                            }
                        }

                        privatBankCourses = formattedCourses
                        with(view) {
                            setPrivatBankCourses(privatBankCourses)
                            hidePrivatBankError()
                        }
                    } else {
                        privatBankCourses = listOf()
                        with(view) {
                            showPrivatBankError()
                            setPrivatBankCourses(listOf())
                        }
                    }
                }
            })
    }


    private fun getNbuBankCourses(date: LocalDate) {
        if(!view.isOnline()) {
            view.showToast(stringProvider.getString(R.string.no_connection_error))
            return
        }

        nbuApi
            .getNbuCoursesOfDate(dateFormatter.getNbuFormat(date))
            .enqueue(object: Callback<List<NbuCourseByDate>> {

                override fun onFailure(call: Call<List<NbuCourseByDate>>, t: Throwable) {
                    view.showToast(stringProvider.getString(R.string.default_error))
                }

                override fun onResponse(
                    call: Call<List<NbuCourseByDate>>,
                    response: Response<List<NbuCourseByDate>>
                ) {
                    val data = response.body()!!
                    nbuBankCourses = data
                    view.setNbuBankCourses(nbuBankCourses)
                    if(data.isNotEmpty()) {
                        view.hideNbuBankError()
                    } else {
                        view.showNbuBankError()
                    }
                }

            })
    }


    override fun onPrivatBankDateBtnClicked() {
        view.showDatePickerDialog(privatBankDate, Bank.PRIVAT_BANK)
    }


    override fun onNbuBankDateBtnClicked() {
        view.showDatePickerDialog(nbuBankDate, Bank.NBU)
    }


    override fun onDatePicked(date: LocalDate, bank: Bank) {
        when(bank) {
            Bank.PRIVAT_BANK -> {
                privatBankDate = date
                getPrivatBankCourses(date)
                view.setPrivatBankDate(dateFormatter.getCommonFormat(privatBankDate))
            }

            Bank.NBU -> {
                nbuBankDate = date
                getNbuBankCourses(date)
                view.setNbuBankDate(dateFormatter.getCommonFormat(nbuBankDate))
            }
        }
    }


    override fun onPrivatBankItemClicked(currencyCode: String) {
        val currentSelected = nbuBankCourses.indexOf(nbuBankCourses.find { it.currencyCode == currencyCode })
        if(currentSelected == -1) {
            view.showToast("Дані НБУ відсутні")
        } else {
            view.setNbuBankItem(previousSelected, currentSelected)
            previousSelected = currentSelected
        }
    }


}