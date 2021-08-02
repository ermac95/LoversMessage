package com.mycodeflow.loversmessage.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycodeflow.loversmessage.R
import com.mycodeflow.loversmessage.databinding.FragmentMessageScreenBinding


class MessageScreen : Fragment(R.layout.fragment_message_screen) {

    private val vb by viewBinding(FragmentMessageScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        //back button setting
        vb.btnBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
        //new message button setting
        vb.btnNewMsg.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionToNewMessage)
        }
    }
    
}