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

        val btnEnglish = findViewById<LinearLayout>(R.id.btnEnglish)
        btnEnglish.setOnClickListener {
            LocaleHelper.setLocale(this, "en")
            getResources()
        }

        val hindiBtn = findViewById<LinearLayout>(R.id.hindiBtn)
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        hindiBtn.setOnClickListener {
            LocaleHelper.setLocale(this, "hi")
            getResources()
        }

        btnBack.setOnClickListener { onBackPressed() }



    }
}