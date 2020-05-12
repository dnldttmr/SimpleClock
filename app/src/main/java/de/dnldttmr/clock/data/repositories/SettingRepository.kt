package de.dnldttmr.clock.data.repositories

import android.content.SharedPreferences
import de.dnldttmr.clock.data.models.Setting
import de.dnldttmr.clock.ui.util.getReadableString

class SettingRepository constructor(val preferences: SharedPreferences) : Repository {

    private val settingTypes: List<Setting.Type> = listOf(Setting.Type.WALLPAPER)

    fun updateSetting(type: Setting.Type, value: String): List<Setting> {
        preferences.edit().putString(type.toString(), value).apply()
        return settingTypes.map {
            Setting(it, preferences.getReadableString(it.toString()))
        }
    }

    fun getSettings(): List<Setting> {
        return settingTypes.map {
            Setting(it, preferences.getReadableString(it.toString()))
        }
    }

}