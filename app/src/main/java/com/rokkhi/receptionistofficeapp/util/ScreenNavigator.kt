package com.rokkhi.receptionistofficeapp.util

import android.app.Activity
import android.content.Intent
import com.rokkhi.receptionistofficeapp.ui.attendance_in.AttendanceInActivity
import com.rokkhi.receptionistofficeapp.ui.attendance_out.AttendanceOutActivity
import com.rokkhi.receptionistofficeapp.ui.main.MainActivity
import com.rokkhi.receptionistofficeapp.ui.parcel_delivery.ParcelDeliveredActivity
import com.rokkhi.receptionistofficeapp.ui.parcel_received.ParcelReceivedActivity
import com.rokkhi.receptionistofficeapp.ui.splash.SplashActivity
import com.rokkhi.receptionistofficeapp.ui.visitor_in.VisitorInActivity
import com.rokkhi.receptionistofficeapp.ui.visitor_out.VisitorOutActivity


object ScreenNavigator {

    fun navigateSplashActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, SplashActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        activity?.finish()
    }


    fun navigateMainActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, MainActivity::class.java))
    }


    fun navigateVisitorInActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, VisitorInActivity::class.java))
    }

    fun navigateVisitorListActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, VisitorOutActivity::class.java))
    }
    fun navigateAttendanceInActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, AttendanceInActivity::class.java))
    }


    fun navigateAttendanceOutActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, AttendanceOutActivity::class.java))
    }

    fun navigateParcelReceivedActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, ParcelReceivedActivity::class.java))
    }

    fun navigateParcelDeliveredActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, ParcelDeliveredActivity::class.java))
    }




}