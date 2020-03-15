package com.antonioleiva.cleanarchitecturesample.framework

import com.antonioleiva.data.LocationPersistenceSource
import com.antonioleiva.domain.Location

class InMemoryLocationPersistenceSource : LocationPersistenceSource {

    private var locations: List<Location> = emptyList()

    override fun getPersistedLocations(): List<Location> = locations

    override fun saveNewLocation(location: Location) {
        locations = locations + location
    }
}