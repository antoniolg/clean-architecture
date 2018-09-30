package com.antonioleiva.cleanarchitecturesample.ui

import com.antonioleiva.domain.Location
import com.antonioleiva.usecases.GetLocations
import com.antonioleiva.usecases.RequestNewLocation
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.coroutines.experimental.bg

class MainPresenter(
    private var view: View?,
    private val getLocations: GetLocations,
    private val requestNewLocation: RequestNewLocation
) {
    interface View {
        fun renderLocations(locations: List<Location>)
    }

    fun onCreate() = launch(UI) {
        val locations = bg { getLocations() }.await()
        view?.renderLocations(locations)
    }

    fun newLocationClicked() = launch(UI) {
        val locations = bg { requestNewLocation() }
        view?.renderLocations(locations.await())
    }

    fun onDestroy() {
        view = null
    }
}