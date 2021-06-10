package com.example.flightappp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Arrival(
    val actual: String?,
    val actual_runway: String?,
    val airport: String,
    val baggage: String?,
    val delay: String?,
    val estimated: String,
    val estimated_runway: String?,
    val gate: String?,
    val iata: String,
    val icao: String,
    val scheduled: String,
    val terminal: String?,
    val timezone: String
):Parcelable