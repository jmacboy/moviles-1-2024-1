package com.example.loginapi.repositories

import com.example.loginapi.api.APIProyecto
import com.example.loginapi.models.dto.LoginRequestDTO
import com.example.loginapi.models.dto.LoginResponseDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserRepository {
    fun doLogin(
        email: String,
        password: String,
        success: (LoginResponseDTO?) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APIProyecto =
            retrofit.create(APIProyecto::class.java)
        service.login(LoginRequestDTO(email, password))
            .enqueue(object : Callback<LoginResponseDTO> {
                override fun onResponse(
                    res: Call<LoginResponseDTO>,
                    response: Response<LoginResponseDTO>
                ) {
                    if (response.code() == 401) {
                        success(null)
                        return
                    }
                    val loginResponse = response.body()
                    success(loginResponse)
                }

                override fun onFailure(res: Call<LoginResponseDTO>, t: Throwable) {
                    failure(t)
                }
            })
    }
}