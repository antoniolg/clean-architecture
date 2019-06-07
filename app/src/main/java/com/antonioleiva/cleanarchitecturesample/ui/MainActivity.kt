package com.antonioleiva.cleanarchitecturesample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.antonioleiva.cleanarchitecturesample.R
import com.antonioleiva.cleanarchitecturesample.framework.FakeLocationSource
import com.antonioleiva.cleanarchitecturesample.framework.InMemoryLocationPersistenceSource
import com.antonioleiva.data.LocationsRepository
import com.antonioleiva.usecases.GetLocations
import com.antonioleiva.usecases.RequestNewLocation
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.currentScope
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val locationsAdapter = LocationsAdapter()
    private val presenter: MainPresenter by currentScope.inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = locationsAdapter

        newLocationBtn.setOnClickListener { presenter.newLocationClicked() }

        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun renderLocations(locations: List<Location>) {
        locationsAdapter.items = locations
    }
}