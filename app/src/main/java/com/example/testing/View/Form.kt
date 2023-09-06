package com.example.testing.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.testing.R
class Form : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_form, container, false)
        var button: Button = view.findViewById(R.id.button)
        var text: EditText = view.findViewById(R.id.editText)

        button.setOnClickListener(View.OnClickListener {
            val bundle = bundleOf("amount" to text.text.toString().toInt())
            findNavController().navigate(R.id.action_form_to_dogInfo,bundle)
        })
        toolBar(view)
        return view
    }


    private fun toolBar(view:View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val titleTextView = toolbar.findViewById<TextView>(R.id.toolbar_title)
        val leftMenuIcon = toolbar.findViewById<ImageView>(R.id.left_icon)
        val rightMenuIcon = toolbar.findViewById<ImageView>(R.id.right_action)

        // Customize the toolbar attributes
        titleTextView.text = "FORM"
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