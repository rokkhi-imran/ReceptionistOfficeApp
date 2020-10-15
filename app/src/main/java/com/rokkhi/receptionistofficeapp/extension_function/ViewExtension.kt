package com.rokkhi.receptionistofficeapp.extension_function

import android.app.Activity
import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

fun AppCompatActivity.setActionbarWithBackBtn(toolbar: Toolbar,title:String){
    setSupportActionBar(toolbar)
    supportActionBar?.title = title
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
}
fun AppCompatActivity.setActionbarWithoutBackBtn(toolbar: Toolbar,title:String){
    setSupportActionBar(toolbar)
    supportActionBar?.title = title
}


fun Activity.startCropImageActivity(){
    CropImage.activity()
        .setGuidelines(CropImageView.Guidelines.ON)
        .setAspectRatio(1,1)
        .start(this)
}

fun<viewHolder,T: RecyclerView.Adapter<viewHolder>> Activity.setUpVerticalRecyclerViewAdapter(view: RecyclerView, viewAdapter:T){
    view.setHasFixedSize(true)
    view.layoutManager = LinearLayoutManager(this)
    view.adapter = viewAdapter
}

fun<viewHolder,T: RecyclerView.Adapter<viewHolder>> Activity.setUpHorizontalRecyclerViewAdapter(view: RecyclerView, viewAdapter:T){
    view.setHasFixedSize(true)
    view.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    view.adapter = viewAdapter
}

fun TextView.getTextFromTv():String = this.text.toString().trim()

fun Activity.shareIntent(message:String){
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT,message)
    startActivity(Intent.createChooser(intent,"Please Select.."))

}

