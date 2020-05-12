package de.dnldttmr.clock.ui.main

import android.app.WallpaperManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import de.dnldttmr.clock.R
import de.dnldttmr.clock.data.models.Time
import de.dnldttmr.clock.ui.util.createBitmap
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.getViewModel
import timber.log.Timber

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private var previousTimeResult: Pair<Boolean, Time?>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel = getViewModel<MainViewModel>().apply { getTime() }

        viewModel.timeLiveData.observe(this, Observer {
            it.second?.let { time ->
                when (time.minute) {
                    in 0..2 -> {
                        hideMinutes(listOf())
                        hideHourForChangedHour(time.hour)
                    }
                    in 3..7 -> {
                        hideMinutes(listOf(minuteFiveContainer, minutePastContainer))
                        hideHourForChangedHour(time.hour)
                    }
                    in 8..12 -> {
                        hideMinutes(listOf(minuteTenContainer, minutePastContainer))
                        hideHourForChangedHour(time.hour)
                    }
                    in 13..17 -> {
                        hideMinutes(listOf(minuteQuarterContainer, minutePastContainer))
                        hideHourForChangedHour(time.hour)
                    }
                    in 18..22 -> {
                        hideMinutes(listOf(minuteTwentyContainer, minutePastContainer))
                        hideHourForChangedHour(time.hour)
                    }
                    in 23..28 -> {
                        hideMinutes(listOf(minuteFiveContainer, minutePreContainer, minuteHalfContainer))
                        hideHourForChangedHour(time.hour + 1)
                    }
                    in 29..32 -> {
                        hideMinutes(listOf(minuteHalfContainer))
                        hideHourForChangedHour(time.hour + 1)
                    }
                    in 33..37 -> {
                        hideMinutes(listOf(minuteFiveContainer, minutePastContainer, minuteHalfContainer))
                        hideHourForChangedHour(time.hour + 1)
                    }
                    in 38..42 -> {
                        hideMinutes(listOf(minuteTwentyContainer, minutePreContainer))
                        hideHourForChangedHour(time.hour + 1)
                    }
                    in 43..47 -> {
                        hideMinutes(listOf(minuteQuarterContainer, minutePreContainer))
                        hideHourForChangedHour(time.hour + 1)
                    }
                    in 48..52 -> {
                        hideMinutes(listOf(minuteTenContainer, minutePreContainer))
                        hideHourForChangedHour(time.hour + 1)
                    }
                    in 53..57 -> {
                        hideMinutes(listOf(minuteFiveContainer, minutePreContainer))
                        hideHourForChangedHour(time.hour + 1)
                    }
                    in 58..60 -> {
                        hideMinutes(listOf())
                        hideHourForChangedHour(time.hour + 1)
                    }
                    else -> {
                        Timber.d("Time Minute Error: %s", time.minute)
                        hideMinutes()
                    }
                }
                val bitmap = main.createBitmap()
                WallpaperManager.getInstance(context).setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK)
            }
        })
    }

    private fun hideHourForChangedHour(hour: Int) {
        when (hour % 12) {
            0 -> hideHours(hourTwelveContainer)
            1 -> hideHours(hourOneContainer)
            2 -> hideHours(hourTwoContainer)
            3 -> hideHours(hourThreeContainer)
            4 -> hideHours(hourFourContainer)
            5 -> hideHours(hourFiveContainer)
            6 -> hideHours(hourSixContainer)
            7 -> hideHours(hourSevenContainer)
            8 -> hideHours(hourEightContainer)
            9 -> hideHours(hourNineContainer)
            10 -> hideHours(hourTenContainer)
            11 -> hideHours(hourElevenContainer)
            else -> {
                Timber.d("Time Hour Error: %s", hour)
                hideHours()
            }
        }
    }

    private fun hideMinutes(exclude: List<View> = emptyList()) {
        minuteFiveContainer.alpha = getDimension(exclude.contains(minuteFiveContainer))
        minuteTenContainer.alpha = getDimension(exclude.contains(minuteTenContainer))
        minuteQuarterContainer.alpha = getDimension(exclude.contains(minuteQuarterContainer))
        minuteTwentyContainer.alpha = getDimension(exclude.contains(minuteTwentyContainer))
        minuteHalfContainer.alpha = getDimension(exclude.contains(minuteHalfContainer))
        minutePreContainer.alpha = getDimension(exclude.contains(minutePreContainer))
        minutePastContainer.alpha = getDimension(exclude.contains(minutePastContainer))
    }

    private fun hideHours(exclude: View? = null) {
        hourOneContainer.alpha = getDimension(exclude == hourOneContainer)
        hourTwoContainer.alpha = getDimension(exclude == hourTwoContainer)
        hourThreeContainer.alpha = getDimension(exclude == hourThreeContainer)
        hourFourContainer.alpha = getDimension(exclude == hourFourContainer)
        hourFiveContainer.alpha = getDimension(exclude == hourFiveContainer)
        hourSixContainer.alpha = getDimension(exclude == hourSixContainer)
        hourSevenContainer.alpha = getDimension(exclude == hourSevenContainer)
        hourEightContainer.alpha = getDimension(exclude == hourEightContainer)
        hourNineContainer.alpha = getDimension(exclude == hourNineContainer)
        hourTenContainer.alpha = getDimension(exclude == hourTenContainer)
        hourElevenContainer.alpha = getDimension(exclude == hourElevenContainer)
        hourTwelveContainer.alpha = getDimension(exclude == hourTwelveContainer)
    }

    //TODO: Transfer to dimens
    private fun getDimension(show: Boolean): Float = if (show) 1.0f else 0.3f
}
