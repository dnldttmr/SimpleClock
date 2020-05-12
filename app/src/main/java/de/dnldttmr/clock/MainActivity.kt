package de.dnldttmr.clock

import android.app.WallpaperManager
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import de.dnldttmr.clock.ui.main.MainFragment
import de.dnldttmr.clock.ui.settings.SettingsFragment
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        Toasty.Config.getInstance()
            .allowQueue(true)
            .setTextSize(12)
            .apply()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        menuContainer.setOnClickListener {
            val settingsFragment = SettingsFragment.newInstance()
            settingsFragment.show(supportFragmentManager, "settings_fragment")
        }
        menuContainer.setOnLongClickListener {
            Toasty.normal(applicationContext, getString(R.string.settings), Toast.LENGTH_SHORT).show()
            true
        }
    }
}
