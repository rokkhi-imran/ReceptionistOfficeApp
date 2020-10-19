package com.rokkhi.receptionistofficeapp.base

import android.app.Application
import android.util.Log
import androidx.databinding.library.BuildConfig
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.rokkhi.receptionistofficeapp.di.DaggerApplicationComponent
import com.rokkhi.receptionistofficeapp.helper.FontsOverride
import com.rokkhi.receptionistofficeapp.helper.SharedPrefHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class RokkhiApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var helper: SharedPrefHelper

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
//        createNotificationServiceChannel()
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/OpenSans-Regular.ttf")
        DaggerApplicationComponent.builder().application(this).build().inject(this)



    }


/*    private fun createNotificationServiceChannel() {
        Timber.e("Creating notification channel ID")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(KeyFrame.NOTIFICATION_CHANNEL_ID, "Survey sync service channel", NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }*/

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}