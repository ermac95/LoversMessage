package com.mycodeflow.loversmessage.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycodeflow.loversmessage.R
import com.mycodeflow.loversmessage.domain.model.LoveCardTemplate
import com.mycodeflow.loversmessage.databinding.PostcardItemBinding

class LoveCardSliderAdapter: RecyclerView.Adapter<LoveCardViewHolder>(){

    private val items: List<LoveCardTemplate> = getListOfLoveCards()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoveCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = PostcardItemBinding.inflate(inflater, parent, false)
        return LoveCardViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LoveCardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun getCurrentItem(itemPosition: Int) = items[itemPosition]

    private fun getListOfLoveCards(): List<LoveCardTemplate> = listOf(
            LoveCardTemplate(R.drawable.love_card_1, "LoveCard #1"),
            LoveCardTemplate(R.drawable.love_card_2, "LoveCard #2"),
            LoveCardTemplate(R.drawable.love_card_3, "LoveCard #3")
    )
}

class LoveCardViewHolder(
    private val itemBinding: PostcardItemBinding
): RecyclerView.ViewHolder(itemBinding.root){

    fun bind(loveCardTemplate: LoveCardTemplate) = with(itemBinding){
        postcardBg.setImageResource(loveCardTemplate.image)
        postcardTitle.text = loveCardTemplate.title
    }
}