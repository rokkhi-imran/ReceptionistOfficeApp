package com.rokkhi.receptionistofficeapp.util

import android.app.Activity
import android.graphics.Color
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
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
}