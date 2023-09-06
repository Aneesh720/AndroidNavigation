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
class DogInfo : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun receiveDataFromAll(data: String) {
        Log.d("Dog INfo",data)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_dog_info, container, false)
        val tv = view.findViewById<TextView>(R.id.textViewAmount)
        tv.text= arguments?.getInt("amount").toString()

        toolBar(view)
        return view
    }



    private fun toolBar(view:View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val titleTextView = toolbar.findViewById<TextView>(R.id.toolbar_title)
        val leftMenuIcon = toolbar.findViewById<ImageView>(R.id.left_icon)
        val rightMenuIcon = toolbar.findViewById<ImageView>(R.id.right_action)

        // Customize the toolbar attributes
        titleTextView.text = "Dog Info"
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

    }
}