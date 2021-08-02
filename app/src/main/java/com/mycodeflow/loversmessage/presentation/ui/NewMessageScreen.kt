package com.mycodeflow.loversmessage.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycodeflow.loversmessage.R
import com.mycodeflow.loversmessage.domain.model.LoveCardItem
import com.mycodeflow.loversmessage.databinding.FragmentNewMessageScreenBinding
import kotlin.math.abs

class NewMessageScreen : Fragment(R.layout.fragment_new_message_screen) {

    private val vb by viewBinding(FragmentNewMessageScreenBinding::bind)
    private val items: List<LoveCardItem> by lazy {
        getListOfLoveCards()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        val pageTransformer = createPageTransformer()
        val slider = vb.cardsSlider
        with(slider){
            adapter = LoveCardSliderAdapter(items)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(pageTransformer)
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

    private fun getListOfLoveCards(): List<LoveCardItem> = listOf(
        LoveCardItem(R.drawable.love_card_1, String.format(getString(R.string.love_card_number, 1))),
        LoveCardItem(R.drawable.love_card_2, String.format(getString(R.string.love_card_number, 2))),
        LoveCardItem(R.drawable.love_card_3, String.format(getString(R.string.love_card_number, 3)))
    )
}