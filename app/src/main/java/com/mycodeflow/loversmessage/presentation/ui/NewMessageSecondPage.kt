package com.mycodeflow.loversmessage.presentation.ui


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.mycodeflow.loversmessage.R
import com.mycodeflow.loversmessage.databinding.FragmentNewMessageSecondPageBinding
import java.text.SimpleDateFormat
import java.util.*

class NewMessageSecondPage : Fragment(R.layout.fragment_new_message_second_page) {

    private val vb by viewBinding(FragmentNewMessageSecondPageBinding::bind)
    private val cal = Calendar.getInstance()
    private var dateOfSending: String? = null
    private var timeOfSending: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        //Back btn setup
        vb.newMsgBtnBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
        //Set Date picker btn
        vb.btnPickDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                getDateListener(),
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        //Set Time picker btn
        vb.btnPickTime.setOnClickListener {
            TimePickerDialog(requireContext(),
                getTimeListener(),
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true)
                .show()
        }
        //Set Send msg btn
        vb.btnSendMsg.setOnClickListener {
            //send date to ViewModel
            Navigation.findNavController(it).navigate(R.id.actionSendMessageToHomeScreen)
        }
    }

    private fun getDateListener(): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setDateSelected()
        }
    }

    private fun setDateSelected() {
        val dateFormat = "dd MMMM yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.US)
        dateOfSending = sdf.format(cal.time)
        vb.fieldDate.text = dateOfSending
    }

    private fun getTimeListener(): TimePickerDialog.OnTimeSetListener {
        return TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            setTimeSelected()
        }
    }

    private fun setTimeSelected(){
        val timeFormat = "HH:mm"
        val sdf = SimpleDateFormat(timeFormat, Locale.US)
        timeOfSending = sdf.format(cal.time)
        vb.fieldTime.text = timeOfSending
    }
}