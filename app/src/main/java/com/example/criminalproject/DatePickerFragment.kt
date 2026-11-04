package com.example.criminalproject

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import java.util.Calendar
import java.util.GregorianCalendar

class DatePickerFragment : DialogFragment() {
    private val args: DatePickerFragmentArgs by navArgs()

    companion object {
        const val REQUEST_KEY_DATE = "REQUEST_KEY_DATE"
        const val BUNDLE_KEY_DATE = "BUNDLE_KEY_DATE"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        calendar.time = args.crimeDate
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)

        val dateListener = DatePickerDialog.OnDateSetListener {
                _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val resultDate = GregorianCalendar(year, month, dayOfMonth).time

            val result = Bundle().apply {
                putSerializable(BUNDLE_KEY_DATE, resultDate)
            }
            setFragmentResult(REQUEST_KEY_DATE, result)
        }

        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )
    }
}