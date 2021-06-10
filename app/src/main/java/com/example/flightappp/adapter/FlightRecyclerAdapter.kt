package com.example.flightappp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flightappp.R
import com.example.flightappp.model.Data
import kotlinx.android.synthetic.main.item_flight.view.*
import java.text.SimpleDateFormat
import java.util.*


class FlightRecyclerAdapter(private val flightList: List<Data>, var onFlightClickListener: OnFlightClickListener ): RecyclerView.Adapter<FlightRecyclerAdapter.ViewHolder>() {
   private lateinit var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlightRecyclerAdapter.ViewHolder {
        context = parent.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_flight,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: FlightRecyclerAdapter.ViewHolder, position: Int) {
       holder.bind()
    }

    override fun getItemCount(): Int {
        return flightList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = flightList[adapterPosition]
            itemView.textInputAirline.setText(item.airline.name)
            val departure = item.departure.icao + " - " + item.departure.estimated.let { formatDate(it,"HH:mm") }
            itemView.textInputDeparture.setText(departure)
            val arrival = item.arrival.icao + " - " + item.arrival.estimated.let { formatDate(it,"HH:mm")}
            itemView.textInputArrival.setText(arrival)
            if (item.departure.delay != null)
                itemView.textInputDelayTime.setText(item.departure.delay.toString() + "mi")
            else
                itemView.textInputDelayTime.setText("0 mi")

            if (item.flight_status == "active")
                itemView.imageStatus.setImageResource(R.drawable.green_circle)
            else if (item.flight_status == "scheduled")
                itemView.imageStatus.setImageResource(R.drawable.yellow_circle)
            else
                itemView.imageStatus.setImageResource(R.drawable.red_circle)

            itemView.setOnClickListener {
                onFlightClickListener.onClick(adapterPosition)
            }

        }

    }

}

fun formatDate(date: String,format: String): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ", Locale.ENGLISH).parse(date)
    val formattedDate = SimpleDateFormat(format, Locale.ENGLISH)
    return (formattedDate.format(date.time))
}

interface OnFlightClickListener{
    fun onClick(position: Int)
}