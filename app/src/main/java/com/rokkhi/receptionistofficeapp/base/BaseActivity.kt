package com.rokkhi.receptionistofficeapp.base

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.rokkhi.receptionistofficeapp.di.ActivityScope
import com.rokkhi.receptionistofficeapp.helper.SharedPrefHelper
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject


@ActivityScope
abstract class BaseActivity<D : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var dataBinding: D
    @Inject
    lateinit var applicationContext: Application
    @Inject
    lateinit var sharedPrefHelper: SharedPrefHelper
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    var activityContext: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        activityContext = this
        dataBinding = DataBindingUtil.setContentView(this, layoutRes())

    }

    @LayoutRes
    abstract fun layoutRes(): Int

    protected fun log(message: String?){
        Timber.e("log: $message")
    }

    protected fun showMessage(message: String?) {
        Snackbar.make(findViewById(android.R.id.content), "" + message, Snackbar.LENGTH_LONG).show()
    }

    protected fun showProgressBar(isLoading: Boolean, view: View) {
        if (isLoading) {
            view.visibility = View.VISIBLE
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        } else {
            view.visibility = View.GONE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    protected fun whiteNotificationBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            window.statusBarColor = Color.parseColor("#FAFAFA")
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        activityContext = null
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}