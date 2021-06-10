package com.example.flightappp.network.service

import com.example.flightappp.model.Flight
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightService {
    @GET("/v1/flights")
    fun getFlights(@Query("access_key") apiKey: String): Call<Flight>
}