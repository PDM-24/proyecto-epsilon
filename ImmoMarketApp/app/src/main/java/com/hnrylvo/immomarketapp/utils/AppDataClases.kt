package com.hnrylvo.inmomarket.utils

import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("day")
    val day: String,
    @SerializedName("startHour")
    val startHour: String,
    @SerializedName("startMinute")
    val startMinute: String,
    @SerializedName("finishHour")
    val finishHour: String,
    @SerializedName("finishMinute")
    val finishMinute: String
)
