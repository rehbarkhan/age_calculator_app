package com.volumesquare.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private  var dateText : TextView? = null
    private  var showDate : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePicker : Button = findViewById(R.id.date_button)
        dateText = findViewById(R.id.date)
        showDate = findViewById(R.id.actual_age)

        setCurrentDate()

        datePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun setCurrentDate(){
        val myCalendar = Calendar.getInstance()
        dateText?.setText("${myCalendar.get(Calendar.DAY_OF_MONTH)}/${myCalendar.get(Calendar.MONTH)+1}/${myCalendar.get(Calendar.YEAR)}")
        showDate?.setText("00000")
    }

    private fun clickDatePicker(){
        val myCalendar  = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        print(year)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            { view, year,month, dayOfMonth ->

                var selectedDate = "$dayOfMonth/${month+1}/$year"
                dateText?.setText(selectedDate)
                val sdf = SimpleDateFormat("dd/mm/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selecteDateinMinute = theDate.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                var currentDateInMinute = currentDate.time/60000

                val timePassed = currentDateInMinute - selecteDateinMinute
                if(timePassed <= 0){
                    showDate?.setText("This can't be true")
                }else{
                    showDate?.setText("$timePassed")
                }
            }
            , year,month,day).show()
    }

}