package com.gwtf.flow

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import com.google.firebase.auth.FirebaseAuth
import com.gwtf.flow.R
import com.gwtf.flow.Utilites.Constants.Business_Name
import com.gwtf.flow.Utilites.Constants.PhoneNumber

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)

        val businessName = view.findViewById<TextView>(R.id.businessName)
        businessName.setText(Business_Name)

        val txt_mobile = view.findViewById<TextView>(R.id.txt_mobile)
        txt_mobile.setText(PhoneNumber)


        val aboutUs = view.findViewById<LinearLayout>(R.id.aboutUs)
        aboutUs.setOnClickListener {
            startActivity(Intent(view.context, AboutUsActivity::class.java))
        }

        val profileSettings = view.findViewById<LinearLayout>(R.id.profileSettings)
        profileSettings.setOnClickListener {
            startActivity(Intent(view.context, ProfileSettingsActivity::class.java))
        }

        val businessSettings = view.findViewById<LinearLayout>(R.id.businessSettings)
        businessSettings.setOnClickListener {
            startActivity(Intent(view.context, BusinessSettingsActivity::class.java))
        }

        val helpSupport = view.findViewById<LinearLayout>(R.id.helpSupport)
        helpSupport.setOnClickListener {
            helpSupport()
        }

        val darkMode = view.findViewById<LinearLayout>(R.id.darkMode)
        darkMode.setOnClickListener {
            startActivity(Intent(view.context, ThemeActivity::class.java))
        }

        val appLanguage = view.findViewById<LinearLayout>(R.id.appLanguage)
        appLanguage.setOnClickListener {
            startActivity(Intent(view.context, ChangeLanguageActivity::class.java))
        }

        val upiId = view.findViewById<LinearLayout>(R.id.upiId)
        upiId.setOnClickListener {
            startActivity(Intent(view.context, UPIActivity::class.java))
        }

        val notificationBtn = view.findViewById<LinearLayout>(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            setNotification()
        }

        val logOutBtn = view.findViewById<LinearLayout>(R.id.logOutBtn)
        logOutBtn.setOnClickListener {
            val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
            mAuth.signOut()
            startActivity(Intent(view.context, SplashActivity::class.java))
        }

        return view
    }

    fun setNotification() {
        val intent = Intent();
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("app_package", context?.packageName)
        intent.putExtra("app_uid", context?.applicationInfo?.uid)
        intent.putExtra("android.provider.extra.APP_PACKAGE", context?.packageName)
        startActivity(intent)
    }

    fun NotificationManagerCompat.areNotificationsFullyEnabled(): Boolean {
        if (!areNotificationsEnabled()) return false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for (notificationChannel in notificationChannels) {
                if (!notificationChannel.isFullyEnabled(this)) return false
            }
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun NotificationChannel.isFullyEnabled(notificationManager: NotificationManagerCompat): Boolean {
        if (importance == NotificationManager.IMPORTANCE_NONE) return false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (notificationManager.getNotificationChannelGroup(group)?.isBlocked == true) return false
        }
        return true
    }


    fun helpSupport() {
        val subject = "Support: Flow"
        val intent = Intent(Intent.ACTION_SEND)
        val addressees = arrayOf("gurjotsinghharika@gmail.com")
        intent.putExtra(Intent.EXTRA_EMAIL, addressees)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.setType("message/rfc822")
        startActivity(Intent.createChooser(intent, "Send Email using:"));
    }
}