package com.rokkhi.receptionistofficeapp.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.SplashActivityBinding
import com.rokkhi.receptionistofficeapp.util.ScreenNavigator

@SuppressLint("SetTextI18n")
class SplashActivity : BaseActivity<SplashActivityBinding>() {
    private lateinit var viewModel: SplashViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)

        ScreenNavigator.navigateMainActivity(activityContext)


    }

    override fun layoutRes(): Int = R.layout.splash_activity

}