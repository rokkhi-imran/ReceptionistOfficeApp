package com.rokkhi.receptionistofficeapp.ui.main

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityMainBinding
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.UserResponse
import com.rokkhi.receptionistofficeapp.util.KeyFrame
import com.rokkhi.receptionistofficeapp.util.ScreenNavigator
import com.rokkhi.receptionistofficeapp.util.StaticFunction
import timber.log.Timber


class MainActivity : BaseActivity<ActivityMainBinding>() {
    lateinit var viewModel: MainViewModel
    private lateinit var userResponse: UserResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        setSupportActionBar(dataBinding.toolbar)

        callGetTokenString()

        dataBinding.attendanceInCardView.setOnClickListener {

            if (StaticFunction.accessPermission(userResponse.data.primaryRoleCode)) {
                ScreenNavigator.navigateAttendanceInActivity(activityContext)
            } else {
                StaticFunction.accessPermissionFailed(activityContext)
            }
/*

            if (userResponse != null && userResponse.data.employee != null && userResponse.data.employee.isNotEmpty() && userResponse.data.employee[0].primaryRoleCode != null) {

                if (StaticFunction.accessPermission(userResponse.data.employee[0].primaryRoleCode)) {
                    ScreenNavigator.navigateAttendanceInActivity(activityContext)
                } else {
                    StaticFunction.accessPermissionFailed(activityContext)
                }
            } else StaticFunction.accessPermissionFailed(activityContext)
*/

        }

        dataBinding.attendanceOutCardView.setOnClickListener {


            if (StaticFunction.accessPermission(userResponse.data.primaryRoleCode)) {
                ScreenNavigator.navigateAttendanceOutActivity(activityContext)
            } else {
                StaticFunction.accessPermissionFailed(activityContext)
            }
        }
        dataBinding.visitorInCardView.setOnClickListener {

            if (StaticFunction.accessPermission(userResponse.data.primaryRoleCode)) {

                ScreenNavigator.navigateVisitorInActivity(activityContext)
            } else {
                StaticFunction.accessPermissionFailed(activityContext)
            }


        }
        dataBinding.visitorOutCardView.setOnClickListener {

            if (StaticFunction.accessPermission(userResponse.data.primaryRoleCode)) {


                ScreenNavigator.navigateVisitorListActivity(activityContext)

            } else {
                StaticFunction.accessPermissionFailed(activityContext)
            }

        }
        dataBinding.parcelReceivedCardView.setOnClickListener {

            if (StaticFunction.accessPermission(userResponse.data.primaryRoleCode)) {


                ScreenNavigator.navigateParcelReceivedActivity(activityContext)

            } else {
                StaticFunction.accessPermissionFailed(activityContext)
            }

        }
        dataBinding.parcelDeliveryCardView.setOnClickListener {

            if (StaticFunction.accessPermission(userResponse.data.primaryRoleCode)) {


                ScreenNavigator.navigateParcelDeliveredActivity(activityContext)

            } else {
                StaticFunction.accessPermissionFailed(activityContext)
            }
        }

    }

    private fun callGetTokenString() {

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val deviceTokenId = task.result


            if (deviceTokenId != null) {
                Timber.e("deviceTokenId = = ==  $deviceTokenId")
                sharedPrefHelper.putString(KeyFrame.DEVICE_TOKEN, "$deviceTokenId")

                callUserResponseApi()
            } else {
                showToast("Device token null ")
            }


        })
    }

    private fun callUserResponseApi() {

        viewModel.getUserDetails(sharedPrefHelper.getString(KeyFrame.DEVICE_TOKEN), "Android").observe(this, Observer { apiResponse -> // todo: remove this line
            when (apiResponse) {
                is ApiResponse.Success -> {
                    showMessage(apiResponse.data.data.name)
                    userResponse = apiResponse.data

                    if (userResponse.data.name.isEmpty()) {
                        showAlertDialogUserNotComplete()
                    }

                }
                is ApiResponse.Progress -> showProgressBar(apiResponse.loading, dataBinding.progressBar)
                is ApiResponse.Failure -> showMessage(apiResponse.errorMessage.message)
            }
        })

    }

    private fun showAlertDialogUserNotComplete() {



        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert !")
        builder.setMessage("This app for restricted user. contact to you company to make a profile for you.\n Thank you")


        builder.setPositiveButton("Yes") { dialog, which ->

            finish()

            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()

        }

        builder.show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_main_setting -> {
                showLogoutAlert(activityContext)
                true
            }

        }
        return true
    }

    private fun showLogoutAlert(activityContext: Activity?) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("LogOut Alert !")
        builder.setMessage("Do you want to logout ?")


        builder.setPositiveButton("Yes") { dialog, which ->
            mAuth.signOut()
            ScreenNavigator.navigateSplashActivity(activityContext)

            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()

        }

        builder.show()

    }

    override fun layoutRes(): Int = R.layout.activity_main
}