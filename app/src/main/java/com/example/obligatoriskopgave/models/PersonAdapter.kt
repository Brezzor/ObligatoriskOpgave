package com.example.obligatoriskopgave.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.obligatoriskopgave.R

class PersonAdapter (
    private val items: List<Person>,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<PersonAdapter.MyViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_card, viewGroup, false)
        return MyViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val item = items[position]
        viewHolder.textViewTitle.text = item.name
        val body: String = "Birthday: ${item.birthYear}/${item.birthMonth}/${item.birthDayOfMonth}\n" +
                            "Age: ${item.age}"
        viewHolder.textViewBody.text = body
    }

    class MyViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var textViewTitle: TextView = itemView.findViewById(R.id.textview_list_item_title)
        val textViewBody: TextView = itemView.findViewById(R.id.textview_list_item_body)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = bindingAdapterPosition
            onItemClicked(position)
        }
    }
}

