package de.dnldttmr.clock.di

import de.dnldttmr.clock.data.repositories.SettingRepository
import de.dnldttmr.clock.data.repositories.TimeRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { TimeRepository() }

    single { SettingRepository(get()) }
}