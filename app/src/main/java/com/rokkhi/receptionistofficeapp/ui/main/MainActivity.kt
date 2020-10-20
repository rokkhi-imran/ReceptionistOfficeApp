package com.rokkhi.receptionistofficeapp.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityMainBinding
import com.rokkhi.receptionistofficeapp.util.ScreenNavigator

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.lifecycleOwner=this;
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)




        dataBinding.attendanceInCardView.setOnClickListener{ ScreenNavigator.navigateAttendanceInActivity(activityContext)}
        dataBinding.attendanceOutCardView.setOnClickListener { ScreenNavigator.navigateAttendanceOutActivity(activityContext) };
        dataBinding.visitorInCardView.setOnClickListener {  ScreenNavigator.navigateVisitorInActivity(activityContext)};
        dataBinding.visitorOutCardView.setOnClickListener{ScreenNavigator.navigateVisitorListActivity(activityContext)};
        dataBinding.parcelReceivedCardView.setOnClickListener{ScreenNavigator.navigateParcelReceivedActivity(activityContext)};
        dataBinding.parcelDeliveryCardView.setOnClickListener {  ScreenNavigator.navigateParcelDeliveredActivity(activityContext)};

    }

    override fun layoutRes(): Int = R.layout.activity_main
}