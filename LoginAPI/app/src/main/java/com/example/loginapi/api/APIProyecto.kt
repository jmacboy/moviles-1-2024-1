package com.example.loginapi.api

import com.example.loginapi.models.dto.LoginRequestDTO
import com.example.loginapi.models.dto.LoginResponseDTO
import com.example.loginapi.models.dto.Restaurant
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface APIProyecto {
    @POST("loginuser")
    fun login(@Body loginRequest: LoginRequestDTO): Call<LoginResponseDTO>

    //El token se tiene que mandar como Bearer + El string que se obtiene de PreferencesRepository.getToken()
    @POST("restaurants")
    fun insertRestaurant(@Header("Authorization") token: String, @Body restaurant: Restaurant): Call<Restaurant>
}