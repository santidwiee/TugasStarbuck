package com.example.tugasstarbuck.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasstarbuck.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coffee.view.*

class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(coffee: Coffee?) {
        if(coffee != null){
            itemView.namaItem.text = coffee.name
            itemView.hargaItem.text = coffee.price
            Picasso.get().load(coffee.image).into(itemView.imgItem)
            //picasso untuk menampilkan library gambar
        }
    }

    companion object{
        fun create(parent: ViewGroup) : MenuViewHolder{
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_coffee, parent, false )
            return MenuViewHolder(view)
        }
    }
}