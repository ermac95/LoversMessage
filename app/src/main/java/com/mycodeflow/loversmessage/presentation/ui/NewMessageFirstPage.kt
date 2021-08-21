package com.mycodeflow.loversmessage.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycodeflow.loversmessage.MyApp
import com.mycodeflow.loversmessage.R
import com.mycodeflow.loversmessage.adapters.LoveCardSliderAdapter
import com.mycodeflow.loversmessage.databinding.FragmentNewMessageFirstPageBinding
import com.mycodeflow.loversmessage.presentation.viewmodels.AppViewModelFactory
import com.mycodeflow.loversmessage.presentation.viewmodels.MessageViewModel
import javax.inject.Inject
import kotlin.math.abs


class NewMessageFirstPage : Fragment(R.layout.fragment_new_message_first_page) {

    private val vb by viewBinding(FragmentNewMessageFirstPageBinding::bind)

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var messageViewModel: MessageViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModel()
    }


    private fun setupViewModel() {
        messageViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MessageViewModel::class.java)
    }

    private fun setupViews() {
        //creating LoveCards slider
        val pageTransformer = createPageTransformer()
        val sliderAdapter = LoveCardSliderAdapter()
        val slider = vb.cardsSlider
        with(slider){
            adapter = sliderAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(pageTransformer)
        }
        //Next page btn
        val imageIndex = slider.currentItem
        val message = vb.msgTextField.text.toString()
        vb.btnNewMsgNext.setOnClickListener {
            messageViewModel.setImageAndMessage(imageIndex, message)
            Navigation.findNavController(it).navigate(R.id.actionNewMessageSecondScreen)
        }
    }

    private fun createPageTransformer(): CompositePageTransformer {
        return CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(30))
            addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.25f
            }
        }
    }
}