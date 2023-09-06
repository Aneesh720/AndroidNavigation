package com.example.testing.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.testing.BaseFragment
import com.example.testing.R

class DogPriceList : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_dog_price_list, container, false)
        var goto: TextView = view.findViewById(R.id.newgoto)

        goto.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_dogPriceList_to_dogInfo)

        })
        toolBar(view)
        return view
    }


    override fun receiveDataFromAll(data: String) {
        Log.d("Dog Price LIst",data)
    }

    private fun toolBar(view:View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val titleTextView = toolbar.findViewById<TextView>(R.id.toolbar_title)
        val leftMenuIcon = toolbar.findViewById<ImageView>(R.id.left_icon)
        val rightMenuIcon = toolbar.findViewById<ImageView>(R.id.right_action)

        // Customize the toolbar attributes
        titleTextView.text = "Price LIST"
        leftMenuIcon.setImageResource(R.drawable.baseline_arrow_back_24)
        rightMenuIcon.isVisible=false
        leftMenuIcon.setOnClickListener{
            if (findNavController().currentBackStackEntry != null) {
                // Navigate back if there are back stack entries
                findNavController().popBackStack()
            } else {
                // No more back stack entries, execute default behavior
            }
        }
        rightMenuIcon.isVisible=true

        rightMenuIcon.setOnClickListener{
            Log.d("API","Api called")
            callTheApi()
        }
    }
}