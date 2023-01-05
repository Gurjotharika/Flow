package com.gwtf.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.gwtf.flow.R
import com.gwtf.flow.Utilites.LocaleHelper

class ChangeLanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_language)
        val sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
        val edit = sharedPreferences.edit()

        val btnEnglish = findViewById<LinearLayout>(R.id.btnEnglish)
        btnEnglish.setOnClickListener {
            edit.putString("AppLang", "en")
            edit.apply()
            LocaleHelper.setLocale(this, "en")
            getResources()
        }

        val hindiBtn = findViewById<LinearLayout>(R.id.hindiBtn)
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val gujaratiBtn = findViewById<LinearLayout>(R.id.gujaratiBtn)
        val punjabiBtn = findViewById<LinearLayout>(R.id.punjabiBtn)

        hindiBtn.setOnClickListener {
            edit.putString("AppLang", "hi")
            edit.apply()
            LocaleHelper.setLocale(this, "hi")
            getResources()
        }

        punjabiBtn.setOnClickListener {
            edit.putString("AppLang", "pa")
            edit.apply()
            LocaleHelper.setLocale(this, "pa")
            getResources()
        }

        btnBack.setOnClickListener { onBackPressed() }



    }
}