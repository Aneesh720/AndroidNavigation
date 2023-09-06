package com.example.testing

import android.os.Looper
import com.example.testing.services.LoginVerifier
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
@RunWith(value=Parameterized::class)
class PasswardTest(val input:String, val expectedValue:String) {


    @Test
    fun passwordVerifying(){
        //Arrange
        val login=LoginVerifier()
        //Act
        val result=login.passwordVerifier(password = input,)
        //Assert
        assertEquals(expectedValue,result)
    }

    companion object{

        @JvmStatic
        @Parameterized.Parameters(name = "{index} : {0} is {1}")
        fun data():List<Array<Any>>{
            return  listOf(
                arrayOf("heyhgjhk","Add the Digits"),
                arrayOf("heyAds"," should be long enough in the Password"),
                arrayOf("heyAF%","Password should be long enough in the Password"),
                arrayOf("heyAF^453","Success"),
                arrayOf("34899995","Add Capital as well as Lower case in the Password"),
                arrayOf("^^$%%%%%","Add the Digits"),
                arrayOf("AFSFEFD1D","Add Capital as well as Lower case in the Password"),
                arrayOf("ASD%^^#$","Add the Digits"),
                arrayOf("Dfkj45435",  "Add Special Character in the Password"),
                arrayOf("ttt*,Wejr43","Success"),
            )
        }
    }
}