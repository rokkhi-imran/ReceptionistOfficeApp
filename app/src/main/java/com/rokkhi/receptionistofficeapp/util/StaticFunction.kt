package com.rokkhi.receptionistofficeapp.util

import android.app.Activity
import android.graphics.Color
import android.text.Editable
import android.util.Log
import android.util.Patterns
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.material.textfield.TextInputEditText
import com.rokkhi.receptionistofficeapp.R
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog

object StaticFunction {

    fun selectImage(activityContext: FragmentActivity?) {
        val setup: PickSetup = PickSetup()
            .setTitle("Choose Photo")
            .setBackgroundColor(Color.WHITE)
            .setButtonOrientation(LinearLayout.HORIZONTAL)
            .setGalleryButtonText("Gallery")
            .setCameraIcon(R.mipmap.camera_colored)
            .setGalleryIcon(R.mipmap.gallery_colored)
            .setCameraToPictures(false)
            .setMaxSize(300)
        PickImageDialog.build(setup) //.setOnClick(this)
            .show(activityContext)
    }

    fun isValidPhone(phoneNo: String): Boolean {
        if (phoneNo[0] != '0') return false
        if (phoneNo.length != 11) return false
        for (i in 1..10) {
            val xx = phoneNo[i]
            if (xx < '0' || xx > '9') return false
        }
        return true
    }


    fun isvalidphone11(phoneno: String): Boolean {
        if (phoneno.isEmpty()) return false
        if (phoneno[0] != '0') return false
        Log.d("TAG", "isvalidphone: bb " + phoneno + " " + phoneno.length)
        if (phoneno.length != 11) return false
        for (i in 0..10) {
            val xx = phoneno[i]
            if (xx < '0' || xx > '9') return false
        }
        return true
    }

    fun getvalidphone(phoneno: String): String? {
        var phoneno = phoneno
        if (phoneno[0] == '8') {
            phoneno = phoneno.substring(2)
        }
        phoneno = phoneno.replace("-", "")
        phoneno = phoneno.replace("+88", "")
        phoneno = phoneno.replace(" ", "")
        return phoneno
    }

    fun isValidEmail(target: String?): Boolean {
        return if (target == null) false else Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun checkValidation(eT: TextInputEditText, text: Editable?, s: String): Boolean {

        return if (text!!.isEmpty()) {
            eT.requestFocus()
            eT.error = s
            false
        }else{
            true
        }

    }


    fun accessPermission(code: String): Boolean = code==KeyFrame.RECEPTION_ROLE_CODE
    fun accessPermissionFailed(activityContext: Activity?) {

        Toast.makeText(activityContext,"access permission not Found ",Toast.LENGTH_SHORT).show()

    }

}