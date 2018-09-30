package com.antonioleiva.cleanarchitecturesample.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.antonioleiva.cleanarchitecturesample.R
import com.antonioleiva.domain.Location
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_location_item.*
import kotlin.properties.Delegates

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    var items: List<Location> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.view_location_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bind(location: Location) {
            with(location) {
                locationCoordinates.text = "${latitude.toPrettifiedString()} | ${longitude.toPrettifiedString()}"
                locationDate.text = date.toPrettifiedString()
            }
        }
    }
}
