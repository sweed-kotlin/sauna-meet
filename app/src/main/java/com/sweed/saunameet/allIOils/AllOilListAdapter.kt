package com.sweed.saunameet.allIOils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sweed.saunameet.database.Oil
import com.sweed.saunameet.databinding.OilAllListItemBinding


class OilCallback() : DiffUtil.ItemCallback<Oil>() {
    override fun areItemsTheSame(oldItem: Oil, newItem: Oil): Boolean {
        return oldItem.oilId == newItem.oilId
    }

    override fun areContentsTheSame(oldItem: Oil, newItem: Oil): Boolean {
        return oldItem == newItem
    }
}

class AllOilListAdapter(val clickListener: OilItemListener) :
    ListAdapter<Oil, AllOilListAdapter.ItemViewHolder>(OilCallback()) {


    class ItemViewHolder private constructor(val binding: OilAllListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Oil,
            clickListener: OilItemListener
        ) {
            binding.oil = item
            binding.oilItemName.text = item.name
            binding.clickListener = clickListener
//            oil_item_image
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = OilAllListItemBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
}

class OilItemListener(val clickListener: (oil: Oil) -> Unit) {
    fun onClick(oil: Oil) = if (oil.isAddButton) clickListener(oil) else null
}