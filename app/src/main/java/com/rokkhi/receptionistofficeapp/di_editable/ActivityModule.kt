package com.rokkhi.receptionistofficeapp.di_editable

import com.rokkhi.receptionistofficeapp.di.ActivityScope
import com.rokkhi.receptionistofficeapp.ui.attendance_in.AttendanceInActivity
import com.rokkhi.receptionistofficeapp.ui.attendance_out.AttendanceOutActivity
import com.rokkhi.receptionistofficeapp.ui.main.MainActivity
import com.rokkhi.receptionistofficeapp.ui.parcel_delivery.ParcelDeliveredActivity
import com.rokkhi.receptionistofficeapp.ui.parcel_received.ParcelReceivedActivity
import com.rokkhi.receptionistofficeapp.ui.splash.SplashActivity
import com.rokkhi.receptionistofficeapp.ui.visitor_in.VisitorInActivity
import com.rokkhi.receptionistofficeapp.ui.visitor_out.VisitorOutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeAttendanceInActivity(): AttendanceInActivity


    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeAttendanceOutActivity(): AttendanceOutActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeVisitorInActivity(): VisitorInActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeVisitorListActivity(): VisitorOutActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeParcelDelieveredActivity(): ParcelDeliveredActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeParcelReceivedActivity(): ParcelReceivedActivity



}