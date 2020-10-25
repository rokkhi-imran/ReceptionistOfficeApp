package com.rokkhi.receptionistofficeapp.util

import android.app.Activity
import android.graphics.Color
import android.text.Editable
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.google.android.material.textfield.TextInputEditText
import com.rokkhi.receptionistofficeapp.R
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import kotlinx.android.synthetic.main.done_alert.view.*
import timber.log.Timber

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
        Timber.d("isValidPhone: bb $phoneno ${phoneno.length}")
        if (phoneno.length != 11) return false
        for (i in 0..10) {
            val xx = phoneno[i]
            if (xx < '0' || xx > '9') return false
        }
        return true
    }

    fun getValidPhone(phoneNo: String): String? {
        var phoneno = phoneNo
        if (phoneno[0] == '8') phoneno = phoneno.substring(2)
        phoneno = phoneno.replace("-", "")
        phoneno = phoneno.replace("+88", "")
        phoneno = phoneno.replace(" ", "")
        return phoneno
    }

    fun checkValidation(eT: TextInputEditText, text: Editable?, s: String): Boolean {
        return if (text!!.isEmpty()) {
            eT.requestFocus(); eT.error = s; false
        } else true
    }

    fun isValidEmail(target: String?): Boolean = if (target == null) false else Patterns.EMAIL_ADDRESS.matcher(target).matches()
    fun checkInputValidationEditText(editText: TextInputEditText, errorMessage: String): Boolean = !checkValidation(editText, editText.text, errorMessage)


    fun accessPermission(code: String): Boolean = code == KeyFrame.RECEPTION_ROLE_CODE
    fun accessPermissionFailed(activityContext: Activity?) {

        Toast.makeText(activityContext, "access permission not Found ", Toast.LENGTH_SHORT).show()
    }

    fun showSuccessAlert(activityContext: Activity?) {

        var alertDialog = AlertDialog.Builder(activityContext!!).create()

        val inflater = LayoutInflater.from(activityContext)
        val convertView = inflater.inflate(R.layout.done_alert, null) as View

        alertDialog.setCancelable(false)
        convertView.okay.setOnClickListener {
            activityContext.finish()
            alertDialog.dismiss()
        }
        alertDialog.setView(convertView)
        alertDialog.show()
    }

}