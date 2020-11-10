package com.oleksandrkarpiuk.db2test.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.db2test.R
import com.oleksandrkarpiuk.db2test.data.remote.privatBank.model.ExchangeRate
import com.oleksandrkarpiuk.db2test.data.remote.privatBank.model.PrivatBankCourseByDate
import kotlinx.android.synthetic.main.privat_bank_course_item.view.*
import java.text.DecimalFormat

class PrivatBankCourseAdapter (
    courses: List<ExchangeRate>
): RecyclerView.Adapter<PrivatBankCourseHolder>() {


    var courses = courses
    var onItemClickedListener: ((String) -> Unit)? = null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrivatBankCourseHolder {
        return PrivatBankCourseHolder(LayoutInflater.from(parent.context).inflate(R.layout.privat_bank_course_item, parent, false))
    }


    override fun getItemCount(): Int {
        return courses.size
    }


    override fun onBindViewHolder(holder: PrivatBankCourseHolder, position: Int) = with(holder) {
        bind(courses[position])
        setOnItemClickedListener(
            courses[position],
            onItemClickedListener
        )
    }


}


class PrivatBankCourseHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


    private val currencyCode = itemView.currencyCodeTv
    private val currencyPurchase = itemView.currencyPurchaseTv
    private val currencySale = itemView.currencySaleTv

    fun bind(course: ExchangeRate) {
        val format = DecimalFormat("0.000")
        currencyCode.text = course.currencyCode
        currencyPurchase.text = format.format(course.purchaseRate)
        currencySale.text = format.format(course.saleRate)
    }


    fun setOnItemClickedListener(course: ExchangeRate, listener: ((String) -> Unit)?) {
        itemView.setOnClickListener {
            listener?.invoke(course.currencyCode)
        }
    }


}
