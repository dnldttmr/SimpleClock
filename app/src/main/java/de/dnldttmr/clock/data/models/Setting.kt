package de.dnldttmr.clock.data.models

data class Setting(val type: Type, val value: String) {

    enum class Type { WALLPAPER }
}