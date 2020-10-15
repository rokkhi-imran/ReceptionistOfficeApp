package com.rokkhi.receptionistofficeapp.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

object PermissionHandler {
    fun checkPermissionForReadExternalStorage(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result: Int = context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    fun requestPermissionForReadExternalStorage(context: Context) {
        try {
            ActivityCompat.requestPermissions(
                (context as Activity?)!!, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                KeyFrame.READ_STORAGE_PERMISSION_REQUEST_CODE
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun checkPermissionForWriteExternalStorage(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result: Int = context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    fun requestPermissionForWriteExternalStorage(context: Context) {
        try {
            ActivityCompat.requestPermissions(
                (context as Activity?)!!, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                KeyFrame.WRITE_EXTERNAL_STORAGE_REQUEST_CODE
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun checkPermissionForCamera(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result: Int = context.checkSelfPermission(Manifest.permission.CAMERA)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    fun requestPermissionForCamera(context: Context) {
        try {
            ActivityCompat.requestPermissions(
                (context as Activity?)!!, arrayOf(Manifest.permission.CAMERA),
                KeyFrame.CAMERA_REQUEST_CODE
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun checkPermissionForAccessFineLocation(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result: Int = context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    fun requestPermissionForAccessFineLocation(context: Context) {
        try {
            ActivityCompat.requestPermissions(
                (context as Activity?)!!, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                KeyFrame.ACCESS_FINE_LOCATION_REQUEST_CODE
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun checkPermissionForAccessBackgroundLocation(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val result: Int = context.checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    fun requestPermissionForAccessBackgroundLocation(context: Context) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ActivityCompat.requestPermissions(
                    (context as Activity?)!!, arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                    KeyFrame.ACCESS_BACKGROUND_LOCATION_REQUEST_CODE
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun checkPermissionForAccessCourseLocation(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result: Int = context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    fun requestPermissionForAccessCourseLocation(context: Context) {
        try {
            ActivityCompat.requestPermissions(
                (context as Activity?)!!, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                KeyFrame.ACCESS_COARSE_LOCATION_REQUEST_CODE
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun checkAllNecessaryPermissionAndRequestIfNotAllowed(context: Context): Int {
//        if (!checkPermissionForAccessBackgroundLocation(context)) return KeyFrame.ACCESS_BACKGROUND_LOCATION_REQUEST_CODE
        if (!checkPermissionForAccessCourseLocation(context)) return KeyFrame.ACCESS_COARSE_LOCATION_REQUEST_CODE
        if (!checkPermissionForAccessFineLocation(context)) return KeyFrame.ACCESS_FINE_LOCATION_REQUEST_CODE
        if (!checkPermissionForCamera(context)) return KeyFrame.CAMERA_REQUEST_CODE
        if (!checkPermissionForReadExternalStorage(context)) return KeyFrame.READ_STORAGE_PERMISSION_REQUEST_CODE
        if (!checkPermissionForWriteExternalStorage(context)) return KeyFrame.WRITE_EXTERNAL_STORAGE_REQUEST_CODE
        return 0
    }
}