package com.hnrylvo.inmomarket.data.networking.model.response

import com.google.gson.annotations.SerializedName

data class UploadPropertyResponse (
    @SerializedName("message")
    val message: String
)