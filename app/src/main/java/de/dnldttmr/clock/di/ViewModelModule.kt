package de.dnldttmr.clock.di

import de.dnldttmr.clock.ui.main.MainViewModel
import de.dnldttmr.clock.ui.settings.SettingsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get()) }

    viewModel { SettingsViewModel(get()) }
}