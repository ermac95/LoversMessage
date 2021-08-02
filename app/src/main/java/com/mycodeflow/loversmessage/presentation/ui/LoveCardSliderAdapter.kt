package com.mycodeflow.loversmessage.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycodeflow.loversmessage.domain.model.LoveCardItem
import com.mycodeflow.loversmessage.databinding.PostcardItemBinding

class LoveCardSliderAdapter(
    private val items: List<LoveCardItem>
): RecyclerView.Adapter<LoveCardViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoveCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = PostcardItemBinding.inflate(inflater, parent, false)
        return LoveCardViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LoveCardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

class LoveCardViewHolder(
    private val itemBinding: PostcardItemBinding
): RecyclerView.ViewHolder(itemBinding.root){

    fun bind(loveCardItem: LoveCardItem) = with(itemBinding){
        loveCardItem.image?.let { postcardBg.setImageResource(it) }
        postcardTitle.text = loveCardItem.title
    }
}