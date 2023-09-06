package com.example.testing

import android.os.Looper
import com.example.testing.services.LoginVerifier
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
class LoginTest {


    @Test
    fun loginVerifying(){
        //Arrange
        val login=LoginVerifier()
        //Act
        val result=login.conditionSatified(email = "anchan@gmail.com", password = "9*Anndj134",)
        //Assert
        assertEquals("Success",result)
    }


}