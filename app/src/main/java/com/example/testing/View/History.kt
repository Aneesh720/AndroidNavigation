package com.example.testing.View

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.example.testing.R
import com.example.testing.adapter.CartAdapter
import com.example.testing.adapter.HistoryAdapter
import com.example.testing.cartList
import com.example.testing.historyList

class History : Fragment() {
//    lateinit var listView: ListView
//    var adapter: HistoryAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View= inflater.inflate(R.layout.fragment_history, container, false)
        //listView = view.findViewById(R.id.historyList)

        val historyButton:Button=view.findViewById<Button>(R.id.historyButton);
        toolBar(view)
        historyButton.setOnClickListener(View.OnClickListener {
            ///Open new Fragment
            findNavController().navigate(R.id.action_history_to_dogInfo)


        })
        //adapter = HistoryAdapter(requireContext(), historyList)
        //listView.adapter = adapter
        return view
    }


    private fun toolBar(view:View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val titleTextView = toolbar.findViewById<TextView>(R.id.toolbar_title)
        val leftMenuIcon = toolbar.findViewById<ImageView>(R.id.left_icon)
        val rightMenuIcon = toolbar.findViewById<ImageView>(R.id.right_action)

        // Customize the toolbar attributes
        titleTextView.text = "History"
        leftMenuIcon.isVisible=false
        leftMenuIcon.setImageResource(R.drawable.baseline_arrow_back_24)
        rightMenuIcon.isVisible=false

    }
}