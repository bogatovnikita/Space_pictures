package com.bogatovnikita.spacepictures.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bogatovnikita.spacepictures.R
import com.bogatovnikita.spacepictures.view.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_container, MainFragment.newInstance()).commit()
        }
    }
}