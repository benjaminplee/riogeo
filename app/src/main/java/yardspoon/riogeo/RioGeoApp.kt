package yardspoon.riogeo

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import yardspoon.riogeo.biz.GeofencesViewModel
import yardspoon.riogeo.data.AppDatabase

private val appModule = module {

    single { AppDatabase.create(androidContext()) }

    viewModel { GeofencesViewModel(get()) }
}

class RioGeoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}