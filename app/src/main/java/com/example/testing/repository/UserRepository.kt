package com.example.testing.repository

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.testing.data.LOGIN_STATUS
import com.example.testing.data.Users

class UserRepository {
    var users = listOf<Users>(
        Users(1, "anchananeesh@gmail.com", "Ajsk*1234"),
        Users(1, "anchan@gmail.com", "Ajsk*1234"),
        Users(1, "aneesh@gmail.com", "Ajsk*1234"),
        Users(1, "anchananeeshraghu@gmail.com", "Ajsk*1234"),
        Users(1, "anee@gmail.com", "Ajsk*1234"),
    )

    fun loginUser(email: String,password:String):LOGIN_STATUS{
        val users= users.filter {
            users ->users.email==email
        }
        return if(users.size==1){
            if(users[0].password==password){
                LOGIN_STATUS.SUCCESS
            }else{
                LOGIN_STATUS.INVALID_PASSWORD
            }
        }else{
            LOGIN_STATUS.INVALID_USER
        }
    }
}