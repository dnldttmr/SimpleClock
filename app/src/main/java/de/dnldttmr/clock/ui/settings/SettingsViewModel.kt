package de.dnldttmr.clock.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.dnldttmr.clock.data.models.Setting
import de.dnldttmr.clock.data.repositories.SettingRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class SettingsViewModel constructor(val settingRepository: SettingRepository) : ViewModel() {

    private var fetchSettingsLiveData: MutableLiveData<List<Setting>> = MutableLiveData()
    val settingsLiveData: LiveData<List<Setting>> get() = fetchSettingsLiveData

    init {
        Timber.d("Injecting SettingsViewModel")
    }

    fun getSettings() {
        viewModelScope.launch {
            val settings = settingRepository.getSettings()
            fetchSettingsLiveData.postValue(settings)
        }
    }

    fun updateSetting(type: Setting.Type, value: String) {
        viewModelScope.launch {
            val settings = settingRepository.updateSetting(type, value)
            fetchSettingsLiveData.postValue(settings)
        }
    }
}