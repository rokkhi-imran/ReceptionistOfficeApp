package com.rokkhi.receptionistofficeapp.util

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.json.JSONObject
import kotlin.reflect.KClass


fun <T: Any> arrayToJSONObject(objectModel: KClass<T>): JSONObject {
    return JSONObject(Gson().toJson(objectModel))
}


fun showMessage(context: AppCompatActivity, message: String?) {
    Snackbar.make(context.findViewById(android.R.id.content), "" + message, Snackbar.LENGTH_LONG).show()
}