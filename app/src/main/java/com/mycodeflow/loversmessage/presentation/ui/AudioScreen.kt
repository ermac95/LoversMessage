package com.mycodeflow.loversmessage.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mycodeflow.loversmessage.R

class AudioScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio_screen, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AudioScreen()
    }
}