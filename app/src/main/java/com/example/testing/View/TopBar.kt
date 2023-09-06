package com.example.testing.View

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.testing.R

class TopBar:DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var dialog= inflater.inflate(R.layout.custom_layout, container, false)

        var one = dialog.findViewById<View>(R.id.one)
        var two = dialog.findViewById<View>(R.id.two)
        var three = dialog.findViewById<View>(R.id.three)
        var four = dialog.findViewById<View>(R.id.fourth)

        one.setOnClickListener(View.OnClickListener {

            Toast.makeText(requireContext(), "one", Toast.LENGTH_SHORT).show()
        })

        two.setOnClickListener(View.OnClickListener {
            Toast.makeText(requireContext(), "two", Toast.LENGTH_SHORT).show()
        })

        three.setOnClickListener(View.OnClickListener {
            Toast.makeText(requireContext(), "third", Toast.LENGTH_SHORT).show()
        })

        four.setOnClickListener(View.OnClickListener {

            Toast.makeText(requireContext(), "four", Toast.LENGTH_SHORT).show()
        })
        return dialog
    }

}