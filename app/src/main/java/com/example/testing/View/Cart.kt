package com.example.testing.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testing.BaseFragment
import com.example.testing.R
import com.example.testing.adapter.CartAdapter
import com.example.testing.cartList

class Cart : BaseFragment() {
    lateinit var listView: ListView
    var adapter: CartAdapter? = null
    override fun receiveDataFromAll(data: String) {
        Log.d("Cart",data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_cart, container, false)

        val historyButton: Button =view.findViewById<Button>(R.id.cartButton);

        toolBar(view)

        historyButton.setOnClickListener(View.OnClickListener {
            ///Open new Fragment
            findNavController().navigate(R.id.action_cart_to_priceList)


        })

//        listView = view.findViewById(R.id.cartList)
//
//        adapter = CartAdapter(requireContext(), cartList)
//        listView.adapter = adapter
//        Log.d("price Size", cartList.size.toString())
        return view
    }

    private fun toolBar(view:View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val titleTextView = toolbar.findViewById<TextView>(R.id.toolbar_title)
        val leftMenuIcon = toolbar.findViewById<ImageView>(R.id.left_icon)
        val rightMenuIcon = toolbar.findViewById<ImageView>(R.id.right_action)

        // Customize the toolbar attributes
        titleTextView.text = "CART"
        leftMenuIcon.isVisible=false
        leftMenuIcon.setImageResource(R.drawable.baseline_arrow_back_24)
        rightMenuIcon.isVisible=false
        rightMenuIcon.isVisible=true

        rightMenuIcon.setOnClickListener{
            Log.d("API","Api called")
            callTheApi()
        }

    }

}