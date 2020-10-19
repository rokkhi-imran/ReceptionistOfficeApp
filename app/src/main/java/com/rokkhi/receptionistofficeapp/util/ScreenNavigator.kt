package com.rokkhi.receptionistofficeapp.util

import android.app.Activity
import android.content.Intent
import com.rokkhi.receptionistofficeapp.ui.attendance_in.AttendanceInActivity
import com.rokkhi.receptionistofficeapp.ui.attendance_out.AttendanceOutActivity
import com.rokkhi.receptionistofficeapp.ui.main.MainActivity
import com.rokkhi.receptionistofficeapp.ui.parcel_delivery.ParcelDelieveredActivity
import com.rokkhi.receptionistofficeapp.ui.parcel_received.ParcelReceivedActivity
import com.rokkhi.receptionistofficeapp.ui.splash.SplashActivity
import com.rokkhi.receptionistofficeapp.ui.visitor_in.VisitorInActivity
import com.rokkhi.receptionistofficeapp.ui.visitor_out.VisitorListActivity


object ScreenNavigator {

    fun navigateSplashActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, SplashActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        activity?.finish()
    }


    fun navigateMainActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        activity?.finish()
    }


    fun navigateVisitorInActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, VisitorInActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        activity?.finish()
    }

    fun navigateVisitorListActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, VisitorListActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        activity?.finish()
    }
    fun navigateAttendanceInActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, AttendanceInActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        activity?.finish()
    }


    fun navigateAttendanceOutActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, AttendanceOutActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        activity?.finish()
    }

    fun navigateParcelReceivedActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, ParcelReceivedActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        activity?.finish()
    }

    fun navigateParcelDeliveredActivity(activity: Activity?) {
        activity?.startActivity(Intent(activity, ParcelDelieveredActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        activity?.finish()
    }


}