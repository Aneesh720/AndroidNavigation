package com.example.testing.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.testing.R
import com.example.testing.data.CartDetails
import com.squareup.picasso.Picasso

//Class MyAdapter
class CartAdapter(private val context: Context, private val list: List<CartDetails>) :
    BaseAdapter() {
    private lateinit var imageView: ImageView
    private lateinit var price: TextView
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.cart_adapter, parent, false)
        imageView = convertView.findViewById(R.id.image)
        price = convertView.findViewById(R.id.price)

        Picasso.get().load(list[position].image).into(imageView)
        price.setText("$ "+list[position].price.toString())
        return convertView
    }
}