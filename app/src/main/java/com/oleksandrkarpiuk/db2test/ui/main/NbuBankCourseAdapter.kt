package com.oleksandrkarpiuk.db2test.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.db2test.R
import com.oleksandrkarpiuk.db2test.data.remote.nbu.model.NbuCourseByDate
import com.oleksandrkarpiuk.db2test.data.remote.privatBank.model.ExchangeRate
import kotlinx.android.synthetic.main.nbu_bank_course_item.view.*
import kotlinx.android.synthetic.main.privat_bank_course_item.view.*
import java.text.DecimalFormat

class NbuBankCourseAdapter(
    courses: List<NbuCourseByDate>
): RecyclerView.Adapter<NbuBankCourseHolder>() {


    var courses = courses




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NbuBankCourseHolder {
        return NbuBankCourseHolder(LayoutInflater.from(parent.context).inflate(R.layout.nbu_bank_course_item, parent, false))
    }


    override fun getItemCount(): Int {
        return courses.size
    }


    override fun onBindViewHolder(holder: NbuBankCourseHolder, position: Int) {
        holder.bind(courses[position], position)
    }


}


class NbuBankCourseHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


    private val currencyName = itemView.currencyNameTv
    private val baseCurrencyCapacity = itemView.baseCurrencyCapacityTv
    private val currencyCapacity = itemView.currencyCapacityTv

    fun bind(course: NbuCourseByDate, position: Int) {

        if(course.isSelected) {
            itemView.setBackgroundColor(Color.parseColor("#88a2f2"))
        } else {
            if (position % 2 == 0) {
                itemView.setBackgroundColor(Color.parseColor("#F0F0F0"))
            } else {
                itemView.setBackgroundColor(Color.WHITE)
            }
        }

        val format = DecimalFormat("0.00")
        currencyName.text = course.currencyName
        baseCurrencyCapacity.text = "100 UAH"
        currencyCapacity.text = format.format(100 / course.currencyCourse) + " ${course.currencyCode}"
    }
}