package com.rokkhi.receptionistofficeapp.util

import android.app.Activity
import android.content.Intent
import com.rokkhi.receptionistofficeapp.ui.splash.SplashActivity


object ScreenNavigator {

    fun navigateSplashActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, SplashActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        activity?.finish()
    }
}