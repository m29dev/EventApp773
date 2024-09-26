package com.example.eventapp773

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(private val dataList: ArrayList<DataClass>): RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

//    var onItemClick : ((DataClass) -> Unit)? = null

    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolderClass(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvName.text = currentItem.name
        holder.rvLocalization.text = currentItem.localization
        holder.rvPrice.text = currentItem.price

//        holder.itemView.setOnClickListener{
//            onItemClick?.invoke(currentItem)
//        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val rvName: TextView = itemView.findViewById(R.id.title_event_name)
        val rvLocalization: TextView = itemView.findViewById(R.id.event_localization)
        val rvPrice: TextView = itemView.findViewById(R.id.event_price)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }
}