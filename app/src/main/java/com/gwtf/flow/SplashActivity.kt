package com.gwtf.flow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.gwtf.flow.Database.SqlDatabase
import com.gwtf.flow.Utilites.Constants

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val db = SqlDatabase(this)

        val mAuth = FirebaseAuth.getInstance()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            if (mAuth.currentUser != null) {
                if (db.business.size <= 0) {
                    val intent = Intent(this, CreateBusinessActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val sharedPref = getSharedPreferences(packageName, MODE_PRIVATE)
                    Constants.Business_Selected =
                        sharedPref.getString("BusinessSelected", null).toString()
                    Constants.Business_Name = sharedPref.getString("BusinessName", null).toString()
                    Constants.Business_Image =
                        sharedPref.getString("BusinessImage", null).toString()
                    Constants.UserName = sharedPref.getString("UserName", null).toString()
                    Constants.PhoneNumber = sharedPref.getString("PhoneNumber", null).toString()
                    Constants.Email = sharedPref.getString("Email", null).toString()
                    Constants.Address = sharedPref.getString("Address", null).toString()

                    startActivity(
                        Intent(
                            this, MainActivity
                            ::class.java
                        )
                    )
                    finish()
                }
            } else {
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)
    }
}