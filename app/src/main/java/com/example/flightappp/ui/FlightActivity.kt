package com.example.flightappp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flightappp.R
import com.example.flightappp.adapter.FlightRecyclerAdapter
import com.example.flightappp.adapter.OnFlightClickListener
import com.example.flightappp.model.Data
import com.example.flightappp.model.Flight
import com.example.flightappp.network.NetworkHelper
import com.example.flightappp.utils.Constant
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_flight.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FlightActivity : AppCompatActivity() {
    private var flightList = mutableListOf<Data>()
    private var networkHelper = NetworkHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight)

        getFlightData()
        setRecyclerAdapter()

        backButton.setOnClickListener {
            val snackbar = Snackbar.make(it,"Çıkış yapmak istiyor musunuz?",Snackbar.LENGTH_LONG).apply {
                setAction("Evet"){
                    clearSavedData()
                    startActivity(Intent(this@FlightActivity,LoginActivity::class.java))
                }
                show()
            }


        }

    }

    private fun clearSavedData() {
        val sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().remove("email").commit()
        sharedPreferences.edit().remove("password").commit()
        sharedPreferences.edit().remove("isRemember").commit()
    }

    private fun getFlightData() {
        networkHelper.flightService?.getFlights(Constant.APIKEY)?.enqueue(object : Callback<Flight>{
            override fun onResponse(call: Call<Flight>, response: Response<Flight>) {
                response.body()?.data.let {
                    it?.forEach { flightList.add(it) }
                }
                rvFlight.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Flight>, t: Throwable) {
                Toast.makeText(
                    this@FlightActivity,
                    "Şuan görüntülenemiyor.",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    private fun setRecyclerAdapter() {
        rvFlight.layoutManager = LinearLayoutManager(this)
        rvFlight.adapter = flightList?.let {
            FlightRecyclerAdapter(it, object : OnFlightClickListener {
                override fun onClick(position: Int) {
                    //Toast.makeText(this@FlightActivity, "$position tıklandı.", Toast.LENGTH_LONG).show()
                    val gotoDetailIntent = Intent(this@FlightActivity,FlightDetailActivity::class.java)
                    gotoDetailIntent.putExtra("flight_departure",flightList[position].departure)
                    gotoDetailIntent.putExtra("flight_arrival",flightList[position].arrival)
                    gotoDetailIntent.putExtra("flight_flight",flightList[position].flight.iata)

                    startActivity(gotoDetailIntent)
                }
            })
        }
    }


}