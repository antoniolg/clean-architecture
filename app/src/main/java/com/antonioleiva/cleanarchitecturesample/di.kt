package com.antonioleiva.cleanarchitecturesample

import com.antonioleiva.cleanarchitecturesample.framework.FakeLocationSource
import com.antonioleiva.cleanarchitecturesample.framework.InMemoryLocationPersistenceSource
import com.antonioleiva.cleanarchitecturesample.ui.MainActivity
import com.antonioleiva.cleanarchitecturesample.ui.MainPresenter
import com.antonioleiva.data.DeviceLocationSource
import com.antonioleiva.data.LocationPersistenceSource
import com.antonioleiva.data.LocationsRepository
import com.antonioleiva.usecases.GetLocations
import com.antonioleiva.usecases.RequestNewLocation
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    factory { LocationsRepository(get(), get()) }
}

val useCaseModule = module {
    factory { GetLocations(get()) }
    factory { RequestNewLocation(get()) }
}

val appModule = module {
    single<LocationPersistenceSource> { InMemoryLocationPersistenceSource() }
    single<DeviceLocationSource> { FakeLocationSource() }
}

val presentersModule = module {

    scope(named<MainActivity>()) {
        scoped { (view: MainPresenter.View) -> MainPresenter(view, get(), get()) }
    }
}