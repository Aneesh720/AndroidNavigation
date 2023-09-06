package com.example.testing

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun receiveDataFromAll(data :String)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as? BaseActivity)?.attachFragment(this)
    }
    fun callTheApi(){
        (activity as? BaseActivity)?.receiveDataSeperatly(activity as MainActivity)
    }

    fun callTheNOtif(){
        (activity as? BaseActivity)?.notify(activity as MainActivity)
    }


    fun sendDataToAll(data: String){
        receiveDataFromAll(data)

        (activity as? BaseActivity)?.receiveDataFromAll( data)

    }

}