package com.example.firebaseapp.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseapp.R
import com.example.firebaseapp.model.ItemEntity

class ItemAdapter(private var items: List<ItemEntity>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    fun updateItems(newItems: List<ItemEntity>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contentTextView: TextView = itemView.findViewById(R.id.message_content)
        private val timeTextView: TextView = itemView.findViewById(R.id.message_timestamp)

        fun bind(item: ItemEntity) {
            contentTextView.text = item.content
            timeTextView.text = item.timestamp
        }
    }
}