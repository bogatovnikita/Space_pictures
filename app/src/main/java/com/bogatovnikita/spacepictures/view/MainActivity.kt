package com.bogatovnikita.spacepictures.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bogatovnikita.spacepictures.R
import com.bogatovnikita.spacepictures.view.main.MainFragment

const val ThemeDefault = 0
const val ThemeFirst = 1
const val ThemeSecond = 2

class MainActivity : AppCompatActivity() {

    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getRealStyle(getCurrentTheme()))
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_container, MainFragment.newInstance()).commit()
        }
    }

    fun setCurrentTheme(currentTheme: Int) {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME, currentTheme)
        editor.apply()
    }

    fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }

    private fun getRealStyle(currentTheme: Int): Int {
        return when (currentTheme) {
            ThemeDefault -> R.style.Theme_SpacePictures
            ThemeFirst -> R.style.Theme_FirstTheme
            ThemeSecond -> R.style.Theme_SecondTheme
            else -> -1
        }
    }
}