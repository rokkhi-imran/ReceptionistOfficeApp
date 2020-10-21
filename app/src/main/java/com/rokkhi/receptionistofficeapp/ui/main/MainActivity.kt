package com.rokkhi.receptionistofficeapp.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityMainBinding
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.util.ScreenNavigator

class MainActivity : BaseActivity<ActivityMainBinding>() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        dataBinding.attendanceInCardView.setOnClickListener {
//            ScreenNavigator.navigateAttendanceInActivity(activityContext)
            viewModel.getUserByPhone("string").observe(this, Observer { apiResponse -> // todo: remove this line
//            viewModel.getUserByPhone(mAuth.currentUser!!.phoneNumber.toString()).observe(this, Observer { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> showMessage(apiResponse.data.data.name)
                    is ApiResponse.Progress -> showProgressBar(apiResponse.loading, dataBinding.progressBar)
                    is ApiResponse.Failure -> showMessage(apiResponse.errorMessage.message)
                }
            })
        }

        dataBinding.attendanceOutCardView.setOnClickListener { ScreenNavigator.navigateAttendanceOutActivity(activityContext) }
//        dataBinding.attendanceOutCardView.setOnClickListener { ScreenNavigator.navigateEditProfileActivity(activityContext) }
        dataBinding.visitorInCardView.setOnClickListener { ScreenNavigator.navigateVisitorInActivity(activityContext) }
        dataBinding.visitorOutCardView.setOnClickListener { ScreenNavigator.navigateVisitorListActivity(activityContext) }
        dataBinding.parcelReceivedCardView.setOnClickListener { ScreenNavigator.navigateParcelReceivedActivity(activityContext) }
        dataBinding.parcelDeliveryCardView.setOnClickListener { ScreenNavigator.navigateParcelDeliveredActivity(activityContext) }

    }

    override fun layoutRes(): Int = R.layout.activity_main
}