package com.example.mvpexample.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.mvpexample.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        findPreference<SwitchPreferenceCompat>(KEY_CHANGE_THEME)?.setOnPreferenceClickListener {

            val switch = it as SwitchPreferenceCompat
            changeTheme(switch.isChecked)
            return@setOnPreferenceClickListener true
        }

        findPreference<SwitchPreferenceCompat>(KEY_CHANGE_SOURCE)?.setOnPreferenceClickListener {

            val switch = it as SwitchPreferenceCompat
            changeDataSource(switch.isChecked)
            return@setOnPreferenceClickListener true
        }
    }

    private fun changeTheme(isChecked: Boolean) {

        if (isChecked) {
            Log.i("tag", "true")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            Log.i("tag", "false")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun changeDataSource(isChecked: Boolean) {
        //TODO
    }

    companion object {
        const val KEY_CHANGE_THEME = "change_theme_setting_key"
        const val KEY_CHANGE_SOURCE = "change_source_setting_key"
    }
}
