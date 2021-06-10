package com.example.flightappp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flightappp.R
import com.example.flightappp.model.Arrival
import com.example.flightappp.model.Departure
import com.example.flightappp.model.FlightX
import kotlinx.android.synthetic.main.activity_flight_detail.*
import java.text.SimpleDateFormat
import java.util.*

class FlightDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_detail)

        val departure: Departure? = intent.getParcelableExtra("flight_departure")
        val arrival: Arrival? = intent.getParcelableExtra("flight_arrival")
        val flight: String? = intent.getStringExtra("flight_flight")

        textInputDetailDeparture.text = departure?.iata
        textInputDetailArrival.text = arrival?.iata
        textInputDepartureAirportName.text = departure?.airport
        textInputArrivalAirportName.text = arrival?.airport
        textInputDepartureDate.text = departure?.estimated?.let {
            formatDate(
                it, "EEE, MMM d\nHH:mm"
            )
        }
        textInputArrivalDate.text = arrival?.estimated?.let {
            formatDate(
                it, "EEE, MMM d\nHH:mm"
            )
        }
        textInputFlightName.text = flight
        textInputGateName.text = departure?.gate
        textInputTerminalName.text = departure?.terminal
        textInputDelayName.text = departure?.delay

        detailTitle.text = flight + " Flight Details"

        buttonCancel.setOnClickListener {
            startActivity(Intent(this,FlightActivity::class.java))
            finish()
        }





    }
    fun formatDate(date: String,format: String): String {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ", Locale.ENGLISH).parse(date)
        val formattedDate = SimpleDateFormat(format, Locale.ENGLISH)
        return (formattedDate.format(date.time))
    }
}