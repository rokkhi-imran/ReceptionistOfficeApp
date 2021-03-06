package com.rokkhi.receptionistofficeapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig
import com.firebase.ui.auth.AuthUI.IdpConfig.PhoneBuilder
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.SplashActivityBinding
import com.rokkhi.receptionistofficeapp.util.KeyFrame.RC_SIGN_IN
import com.rokkhi.receptionistofficeapp.util.ScreenNavigator
import java.util.*

@SuppressLint("SetTextI18n")
class SplashActivity : BaseActivity<SplashActivityBinding>() {

    private lateinit var viewModel: SplashViewModel
    override fun layoutRes(): Int = R.layout.splash_activity

    private lateinit var mAuthListener: AuthStateListener
    private lateinit var phoneConfigWithDefaultNumber: IdpConfig

    //todo: delay screen navigator 3 sec after firebase token id generated with kotlin coroutine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)

        mAuthListener = AuthStateListener {
            if (it.currentUser == null) gosignpage()
            else ScreenNavigator.navigateMainActivity(activityContext)
        }

    }

    //check stroage Permission End
    private fun gosignpage() {
        val whitelistedCountries: MutableList<String> = ArrayList()
        whitelistedCountries.add("in")
        whitelistedCountries.add("bd")
        phoneConfigWithDefaultNumber = PhoneBuilder()
            .setDefaultCountryIso("bd")
            .setWhitelistedCountries(whitelistedCountries)
            .build()
        signInPhone(dataBinding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            handleSignInResponse(resultCode, data)
        }
    }


    private fun handleSignInResponse(resultCode: Int, data: Intent?) {
        val response = IdpResponse.fromResultIntent(data)
        if (resultCode == RESULT_OK) {
            //todo: handle sign in response in tab
        } else {
            if (response == null) {
                showMessage(getString(R.string.sign_in_cancelled))
                return
            }
            if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                showMessage(getString(R.string.no_internet_connection))
                return
            }
            if (response.error!!.errorCode == ErrorCodes.UNKNOWN_ERROR) {
                showMessage(getString(R.string.unknown_error))
                return
            }
        }
    }

    fun signInPhone(view: View?) {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(
                    listOf(phoneConfigWithDefaultNumber)
                )
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onStart() {
        super.onStart()
        mAuthListener.let { mAuth.addAuthStateListener(it) }
    }

    override fun onStop() {
        super.onStop()
        mAuth.removeAuthStateListener(mAuthListener!!)
    }


}