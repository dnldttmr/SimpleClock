package de.dnldttmr.clock

import android.app.Application
import de.dnldttmr.clock.di.applicationModule
import de.dnldttmr.clock.di.repositoryModule
import de.dnldttmr.clock.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(applicationModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}