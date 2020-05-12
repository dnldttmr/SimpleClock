package de.dnldttmr.clock.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.dnldttmr.clock.data.models.Time
import de.dnldttmr.clock.data.repositories.TimeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.concurrent.fixedRateTimer

class MainViewModel constructor(val timeRepository: TimeRepository) : ViewModel() {

    private var fetchTimeLiveData: MutableLiveData<Pair<Boolean, Time?>> = MutableLiveData()
    private var previousTimeResult: Pair<Boolean, Time?>? = null
    val timeLiveData: LiveData<Pair<Boolean, Time?>> get() = fetchTimeLiveData

    init {
        Timber.d("Injecting MainViewModel")
    }

    fun getTime() {
        viewModelScope.launch {
            fetchTimeLiveData.postValue(Pair(true, null))
            fixedRateTimer("", false, 0L, 10000) {
                val time = timeRepository.getTime()
                val result = Pair(false, time)
                if (previousTimeResult != result) {
                    previousTimeResult = result
                    fetchTimeLiveData.postValue(result)
                }
            }
        }
    }
}
