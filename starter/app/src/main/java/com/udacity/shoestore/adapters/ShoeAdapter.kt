package com.udacity.shoestore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe


class ShoeAdapter : ListAdapter<Shoe, ShoeAdapter.ShoeViewHolder>(Differ) {

    inner class ShoeViewHolder(val binding: ItemShoeBinding) :
        RecyclerView.ViewHolder(binding.root)

    object Differ : DiffUtil.ItemCallback<Shoe>() {
        override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe) = oldItem == newItem
    }

    override fun getItemCount() = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ShoeViewHolder(
            ItemShoeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            bShoe = item
            materialCardView.setOnClickListener {
                onclickListener?.let { it(item) }
            }
        }
    }

    private var onclickListener : ((Shoe) -> Unit)? = null

    fun setOnItemClickListener(listener: (Shoe) -> Unit) {
        onclickListener = listener
    }

}