package com.hnrylvo.inmomarket.data.repository

import com.hnrylvo.inmomarket.data.networking.ApiClient
import com.hnrylvo.inmomarket.data.networking.model.request.UploadPropertyRequest

class PropertyRepository : BaseRepository() {
    private val apiService = ApiClient.apiService

    suspend fun uploadProperty(request: UploadPropertyRequest, token: String) = fetchData {
        apiService.uploadProperty(
            token = token,
            api = request
        )
    }

    suspend fun getProperties(token: String) = fetchData {
        apiService.getAllProperties(
            token = token
        )
    }

    suspend fun getProperty(token: String, id: String) = fetchData {
        apiService.getPropertyById(
            token = token,
            id = id
        )
    }
}