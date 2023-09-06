package com.example.testing.repository

import com.example.testing.data.DogDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
interface DogApi {
    @GET("/api/breeds/image/random")
    suspend fun getDogs() : Response<DogDetails>
}