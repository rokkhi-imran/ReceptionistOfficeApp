package com.rokkhi.receptionistofficeapp.ui.visitor_in

import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.adapter.AdapterEmployeeList
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityVisitorInBinding
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.EmployeeListData
import com.rokkhi.receptionistofficeapp.util.KeyFrame
import com.rokkhi.receptionistofficeapp.util.StaticFunction
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.employee_list_alert.view.*
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File

class VisitorInActivity : BaseActivity<ActivityVisitorInBinding>() ,IPickResult,AdapterEmployeeList.OnAdapterItemClickListener{

    lateinit var viewModel: VisitorInViewModel

    var mFileUri = ""
    private var imageBitmap: Bitmap? = null

    private lateinit var adapterEmployeeList: AdapterEmployeeList


     var emloyeData :ArrayList<EmployeeListData> = ArrayList()

    override fun layoutRes(): Int =R.layout.activity_visitor_in


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       dataBinding.lifecycleOwner=this
        viewModel= ViewModelProvider(this, viewModelFactory).get(VisitorInViewModel::class.java)


        adapterEmployeeList = AdapterEmployeeList()

        getEmployeeList()

        dataBinding.userPhotoIV.setOnClickListener { StaticFunction.selectImage(this) }

        dataBinding.imageUploadTV.setOnClickListener { StaticFunction.selectImage(this) }

        dataBinding.SubmitUserInfoBtn.setOnClickListener { checkInputValidation() }

        dataBinding.employeeET.setOnClickListener { showEmployeeAlertDialog() }

    }

    private fun getEmployeeList() {


        viewModel.getEmployeeList(
            sharedPrefHelper.getString(KeyFrame.USER_ID).toInt(), "", "",
            sharedPrefHelper.getString(KeyFrame.COMPANY_ID).toInt(), sharedPrefHelper.getString(KeyFrame.BRANCH_ID).toString(),
            "", "", ""
        ).observe(this, Observer {
            when (it) {
                is ApiResponse.Success -> emloyeData = it.data.data as ArrayList<EmployeeListData>
                is ApiResponse.Progress -> showProgressBar(it.loading, dataBinding.progressBar)
                is ApiResponse.Failure -> logThisWithToast(it.errorMessage.message)
                is ApiResponse.ErrorCode -> logThis(it.errorCode.toString())
            }
        })

    }

    private fun showEmployeeAlertDialog(){

        val alertDialog = AlertDialog.Builder(activityContext!!).create()

        val inflater = LayoutInflater.from(activityContext)
        val convertView = inflater.inflate(R.layout.employee_list_alert, null) as View

        convertView.recyclerView.layoutManager=LinearLayoutManager(this)
        convertView.recyclerView.setHasFixedSize(true)
        convertView.recyclerView.adapter = adapterEmployeeList
        adapterEmployeeList.setListToAdapter(emloyeData)
        adapterEmployeeList.setOnAdapterItemClickListener(this)
        alertDialog.setView(convertView)
        alertDialog.show()

    }


    private fun checkInputValidation() {
        if (!StaticFunction.checkValidation(dataBinding.userNameET, dataBinding.userNameET.text, " ভিজিটর নাম ? ")) {
            return
        }

        if (!StaticFunction.checkValidation(dataBinding.phoneNoET, dataBinding.phoneNoET.text, "ফোন নম্বার দিন !")) {
            return
        }

        if (!StaticFunction.checkValidation(dataBinding.puposeET, dataBinding.puposeET.text, " কি উদ্দ্যেশে এসেছে ? ")) {
            return
        }

        if (!StaticFunction.checkValidation(dataBinding.addressET, dataBinding.addressET.text, "ঠিকানা ইনপুট দিন ! ")) {
            return
        }

        if (imageBitmap!=null){
            imageUploadApi(imageBitmap!!)
        }else{

              callApiToUploadData("")
        }


    }



    private fun imageUploadApi(imageBitmap: Bitmap?) {


        val bytes = ByteArrayOutputStream()

        imageBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

        val path: String = MediaStore.Images.Media.insertImage(activityContext?.contentResolver, imageBitmap, "Title", null)
        val uri: Uri = Uri.parse(path)

        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val idx: Int? = cursor?.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        val file: File = File(cursor?.getString(idx!!))


        Log.e("TAG", "imageUploadApi File: $file")

        showProgressBar(true, dataBinding.progressBar)

        AndroidNetworking.upload(KeyFrame.imageUploadURL)
            .addMultipartFile("image", file) // posting any type of file
            .addMultipartParameter("folderName", "Visitor")
            .addMultipartParameter("subFolderName", "CompanyName")//TODO company name bosbe
            .addMultipartParameter("fileName", System.currentTimeMillis().toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        val imageDownloadLink = response.getString("url")
                        logThis(imageDownloadLink)
                        callApiToUploadData(imageDownloadLink)


                    } catch (e: JSONException) {
                        showProgressBar(false, dataBinding.progressBar)

                        showToast("Failed to upload image ")
                    }
                }

                override fun onError(error: ANError) {
                    showProgressBar(false, dataBinding.progressBar)
                    // handle error
                    showToast("Image Upload Problem wait some Time Later")
                    Log.e("TAG = ", "onError: " + error.errorBody)
                    Log.e("TAG = ", "onError: " + error.message)
                    Log.e("TAG = ", "onError: $error")
                }
            })


    }


    private fun callApiToUploadData(imageLink: String) {

        viewModel.addVisitor(
            0, "", "", sharedPrefHelper.getString(KeyFrame.COMPANY_ID).toInt(), dataBinding.userNameET.text.toString(), sharedPrefHelper.getString(KeyFrame.COMPANY_ID),
            dataBinding.addressET.text.toString(), dataBinding.phoneNoET.text.toString(), "", dataBinding.puposeET.text.toString(), imageLink, imageLink,
            sharedPrefHelper.getString(KeyFrame.BRANCH_ID).toInt(), sharedPrefHelper.getString(KeyFrame.DEPARTMENT_ID).toInt(), sharedPrefHelper.getString(KeyFrame.USER_ID).toInt(),
            1, 1 //TODO responder id and associated employee should be changed
        ).observe(this, Observer {
            when (it) {
                is ApiResponse.Success -> showMessage("-----------------${it.data.status}---------------")
                is ApiResponse.Progress -> showProgressBar(it.loading, dataBinding.progressBar)
                is ApiResponse.Failure -> logThis(it.errorMessage.message)
                is ApiResponse.ErrorCode -> logThis(it.errorCode.message)
            }
        })

    }


    override fun onPickResult(r: PickResult?) {  if (r!!.error == null) {
        if (r?.error == null) {

            dataBinding.userPhotoIV.setImageURI(null);

            mFileUri = r?.uri.toString();
            imageBitmap = r?.bitmap;

            dataBinding.userPhotoIV.setImageURI(r?.uri);

        } else {

            Toast.makeText(this, r.error.message, Toast.LENGTH_LONG).show();
        }

    }
    }

    override fun onItemClick(employeeListData: EmployeeListData) {
        showToast(employeeListData.address)
    }
}