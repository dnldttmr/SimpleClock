package de.dnldttmr.clock.data.repositories

import de.dnldttmr.clock.data.models.Time
import java.util.*

class TimeRepository : Repository {

    fun getTime(): Time {
        val calendar = Calendar.getInstance()
        val localCurrentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val localCurrentMinute = calendar.get(Calendar.MINUTE)
        return Time(localCurrentHour, localCurrentMinute)
    }
}