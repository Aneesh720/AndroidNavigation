package com.example.testing

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

abstract class BaseActivity : AppCompatActivity() {
    private val attachedFragments = mutableListOf<BaseFragment>()



    abstract fun notify(data: Any)
    abstract fun receiveDataFromAll(data: String)

    abstract fun receiveDataSeperatly(reciever:Any?)

    fun attachFragment(fragment: BaseFragment) {
        attachedFragments.add(fragment)
    }

    fun sendDataToALl(data: String) {
        receiveDataFromAll(data)
        var i=1
        supportFragmentManager.fragments.forEach { fragment ->
            Log.d("count",i.toString())
            i++
            Log.d("ittertion",fragment.toString())
            if(fragment is NavHostFragment){
                fragment.childFragmentManager.fragments.forEach {
                    if(it is BaseFragment){
                        Log.d("ittertion","called")
                        it.receiveDataFromAll(data)
                    }
                }
            }


        }
    }
}