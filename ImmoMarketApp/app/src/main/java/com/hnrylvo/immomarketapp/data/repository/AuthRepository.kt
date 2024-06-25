package com.hnrylvo.inmomarket.data.repository

import com.hnrylvo.inmomarket.data.networking.ApiClient
import com.hnrylvo.inmomarket.data.networking.model.request.LoginRequest
import com.hnrylvo.inmomarket.data.networking.model.request.RegisterRequest

class AuthRepository : BaseRepository() {
    private val apiService = ApiClient.apiService

    suspend fun login(request: LoginRequest) = fetchData { apiService.login(request) }
    suspend fun register(request: RegisterRequest) = fetchData { apiService.register(request) }

}