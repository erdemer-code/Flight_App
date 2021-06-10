package com.example.flightappp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class FlightX(
    val codeshared: Any?,
    val iata: String,
    val icao: String,
    val number: String
)