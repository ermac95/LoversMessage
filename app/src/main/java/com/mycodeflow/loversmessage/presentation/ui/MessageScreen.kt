package com.mycodeflow.loversmessage.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycodeflow.loversmessage.MyApp
import com.mycodeflow.loversmessage.R
import com.mycodeflow.loversmessage.databinding.FragmentMessageScreenBinding
import com.mycodeflow.loversmessage.presentation.viewmodels.AppViewModelFactory
import com.mycodeflow.loversmessage.presentation.viewmodels.MessageViewModel
import javax.inject.Inject


class MessageScreen : Fragment(R.layout.fragment_message_screen) {

    private val vb by viewBinding(FragmentMessageScreenBinding::bind)

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var messageViewModel: MessageViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupViewModels()
        setupViews()
    }

    private fun setupViews() {
        lifecycleScope.launchWhenResumed {
            Log.d("myLogs", "MessageScreen started")
            messageViewModel.getCurrentMessages()
        }
    }

    private fun setupViewModels() {
        messageViewModel = ViewModelProvider(this, viewModelFactory)
                .get(MessageViewModel::class.java)
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