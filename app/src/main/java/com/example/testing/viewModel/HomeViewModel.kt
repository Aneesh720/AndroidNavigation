package com.example.testing.viewModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object HomeViewModel {

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl("https://dog.ceo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}