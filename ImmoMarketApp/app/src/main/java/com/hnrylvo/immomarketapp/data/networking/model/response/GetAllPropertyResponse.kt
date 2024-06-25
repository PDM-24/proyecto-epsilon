package com.hnrylvo.inmomarket.data.networking.model.response

import com.google.gson.annotations.SerializedName
import com.hnrylvo.inmomarket.utils.Schedule
import java.io.File


data class GetAllPropertyResponse(
    @SerializedName("_id")
    val propertyId: String,
    @SerializedName("propertyType")
    val propertyType: String,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("municipality")
    val municipality: String,
    @SerializedName("department")
    val department: String,
    @SerializedName("propertyAddress")
    val propertyAddress: String,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("propertySize")
    val propertySize: String,
    @SerializedName("propertyBedrooms")
    val propertyBedrooms: String,
    @SerializedName("propertyBathrooms")
    val propertyBathrooms: String,
    @SerializedName("propertyFloors")
    val propertyFloors: String,
    @SerializedName("propertyParking")
    val propertyParking: String,
    @SerializedName("propertyFurnished")
    val propertyFurnished: String,

    @SerializedName("propertyDescription")
    val propertyDescription: String,
    @SerializedName("propertyPrice")
    val propertyPrice: String,
    @SerializedName("scheduleViewing")
    val scheduleViewing: List<Schedule>,
    @SerializedName("seller")
    val seller: Seller,

//    @SerializedName("images")
//    val images: List<File>
)

data class Seller(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("lastName")
    val lastName: String,
)