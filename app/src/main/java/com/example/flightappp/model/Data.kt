package com.example.flightappp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class Data(
    @SerializedName("aircraft")
    val aircraft: Any,
    @SerializedName("airline")
    val airline: Airline,
    @SerializedName("arrival")
    val arrival: Arrival,
    @SerializedName("departure")
    val departure: Departure,
    @SerializedName("flight")
    val flight: FlightX,
    @SerializedName("flight_date")
    val flight_date: String,
    @SerializedName("flight_status")
    val flight_status: String,
    @SerializedName("live")
    val live: Any
)