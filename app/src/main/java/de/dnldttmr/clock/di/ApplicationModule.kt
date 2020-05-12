package de.dnldttmr.clock.di

import android.content.Context
import de.dnldttmr.clock.data.PREFERENCES_FILE_KEY
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val applicationModule = module {
    
    single { androidApplication().getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE) }
}