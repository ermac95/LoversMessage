package com.mycodeflow.loversmessage.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycodeflow.loversmessage.R
import com.mycodeflow.loversmessage.databinding.FragmentHomeScreenBinding


class HomeScreen : Fragment(R.layout.fragment_home_screen) {

    private val vb by viewBinding(FragmentHomeScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        //navigate to Message Screen
        vb.messageIcon.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionHomeToMessage)
        }
        //navigate to Audio Screen
        vb.audioIcon.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionHomeToAudio)
        }
        //navigate to Photo Screen
        vb.photoIcon.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionHomeToPhoto)
        }
        //navigate to Video Screen
        vb.videoIcon.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionHomeToVideo)
        }
        vb.btnRegister.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionToRegister)
        }
        vb.btnAddLover.setOnClickListener {
            //Navigate to add Lover Screen
        }
    }
}