package com.hnrylvo.inmomarket.data.networking

import com.hnrylvo.inmomarket.data.networking.model.request.LoginRequest
import com.hnrylvo.inmomarket.data.networking.model.request.RegisterRequest
import com.hnrylvo.inmomarket.data.networking.model.request.UploadPropertyRequest
import com.hnrylvo.inmomarket.data.networking.model.response.GetAllPropertyResponse
import com.hnrylvo.inmomarket.data.networking.model.response.LoginResponse
import com.hnrylvo.inmomarket.data.networking.model.response.RegisterResponse
import com.hnrylvo.inmomarket.data.networking.model.response.UploadPropertyResponse
import com.hnrylvo.inmomarket.data.utils.ApiConstants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST(value = ApiConstants.LOGIN_PATH)
    suspend fun login(@Body api: LoginRequest) : LoginResponse

    @POST(value = ApiConstants.REGISTER_PATH)
    suspend fun register(@Body api: RegisterRequest) : RegisterResponse

    @POST(value = ApiConstants.UPLOAD_PROPERTY_PATH)
    suspend fun uploadProperty(@Header ("Authorization") token: String, @Body api: UploadPropertyRequest) : UploadPropertyResponse

    @GET(value = ApiConstants.GET_ALL_PROPERTIES_PATH)
    suspend fun getAllProperties(@Header ("Authorization") token: String) : List<GetAllPropertyResponse>

    @GET(value = ApiConstants.GET_PROPERTY_BY_ID_PATH)
    suspend fun getPropertyById(@Header ("Authorization") token: String, @Path("id") id: String) : GetAllPropertyResponse

}