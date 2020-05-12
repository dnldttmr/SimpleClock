package de.dnldttmr.clock.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import de.dnldttmr.clock.R
import de.dnldttmr.clock.data.models.Setting
import de.dnldttmr.clock.ui.util.fromReadableString
import de.dnldttmr.clock.ui.util.toReadableString
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.settings_fragment.*
import org.koin.android.viewmodel.ext.android.getViewModel

class SettingsFragment : DialogFragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel = getViewModel<SettingsViewModel>().apply { getSettings() }

        viewModel.settingsLiveData.observe(this, Observer {
            it.firstOrNull { it.type == Setting.Type.WALLPAPER }?.let { wallpaperCheckBox.isChecked = it.value.fromReadableString() }
        })

        wallpaperCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.updateSetting(Setting.Type.WALLPAPER, isChecked.toReadableString())
        }
        wallpaperContainer.setOnLongClickListener {
            Toasty.normal(requireContext(), getString(R.string.settings_wallpaper), Toast.LENGTH_SHORT).show()
            true
        }
    }
}