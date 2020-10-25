package com.rokkhi.receptionistofficeapp.ui.parcel_received

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
import com.rokkhi.receptionistofficeapp.databinding.ActivityParcelInBinding
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


class ParcelReceivedActivity : BaseActivity<ActivityParcelInBinding>(), IPickResult, AdapterEmployeeList.OnAdapterItemClickListener {

    var mFileUri = ""
    private var imageBitmap: Bitmap? = null

    override fun layoutRes(): Int = R.layout.activity_parcel_in
    lateinit var viewModel: ParcelReceivedViewModel


    private lateinit var adapterEmployeeList: AdapterEmployeeList


    var employeeDataList: ArrayList<EmployeeListData> = ArrayList()
    lateinit var employeeData: EmployeeListData

    private lateinit var alertDialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(ParcelReceivedViewModel::class.java)
        AndroidNetworking.initialize(getApplicationContext());

        adapterEmployeeList = AdapterEmployeeList()
        getEmployeeList()

        dataBinding.userPhotoIV.setOnClickListener { StaticFunction.selectImage(this) }
        dataBinding.imageUploadTV.setOnClickListener { StaticFunction.selectImage(this) }
        dataBinding.SubmitUserInfoBtn.setOnClickListener {


            if (checkInputValidation()) {

                if (employeeData == null || employeeData.id.toString().isEmpty()) {
                    Toast.makeText(activityContext, "Select Employee", Toast.LENGTH_SHORT).show();
                    return@setOnClickListener

                } else {
                    if (imageBitmap != null) {

                        imageUploadApi(imageBitmap)


                    } else {
                        callApiToUploadData("")
                    }
                }


            }
        }

        dataBinding.whereParcelCame.setOnClickListener { showEmployeeAlertDialog() }
    }

    private fun showEmployeeAlertDialog() {

        alertDialog = AlertDialog.Builder(activityContext!!).create()

        val inflater = LayoutInflater.from(activityContext)
        val convertView = inflater.inflate(R.layout.employee_list_alert, null) as View

        convertView.recyclerView.layoutManager = LinearLayoutManager(this)
        convertView.recyclerView.setHasFixedSize(true)
        convertView.recyclerView.adapter = adapterEmployeeList
        adapterEmployeeList.setListToAdapter(employeeDataList)
        adapterEmployeeList.setOnAdapterItemClickListener(this)
        alertDialog.setView(convertView)
        alertDialog.show()

    }

    private fun getEmployeeList() {


        viewModel.getEmployeeList(
            sharedPrefHelper.getString(KeyFrame.USER_ID).toInt(), "", "",
            sharedPrefHelper.getString(KeyFrame.COMPANY_ID).toInt(), sharedPrefHelper.getString(KeyFrame.BRANCH_ID).toString(),
            "", "", ""
        ).observe(this, Observer {
            when (it) {
                is ApiResponse.Success -> employeeDataList = it.data.data as ArrayList<EmployeeListData>
                is ApiResponse.Progress -> showProgressBar(it.loading, dataBinding.progressBar)
                is ApiResponse.Failure -> logThisWithToast(it.errorMessage.message)
                is ApiResponse.ErrorCode -> logThis(it.errorCode.toString())
            }
        })

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


        Log.e("TAG", "imageUploadApi File: " + file)

        showProgressBar(true, dataBinding.progressBar)

        AndroidNetworking.upload(KeyFrame.imageUploadURL)
            .addMultipartFile("image", file) // posting any type of file
            .addMultipartParameter("folderName", "parcel")
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


        viewModel.addParcel(
            requesterProfileId = "",
            limit = "",
            pageId = "",
            companyId = sharedPrefHelper.getString(KeyFrame.COMPANY_ID).toInt(),
            name = dataBinding.parcelNameET.text.toString(),
            company = dataBinding.parcelCompanyET.text.toString(),
            image = imageLink,
            thumbImage = imageLink,
            associatedEmployee = 0,
            associatedLoggedinDeviceId = sharedPrefHelper.getString(KeyFrame.USER_ID),
            receptionistId = 0,
            departmentId = sharedPrefHelper.getString(KeyFrame.DEPARTMENT_ID).toInt(),
            branchId = sharedPrefHelper.getString(KeyFrame.BRANCH_ID).toInt()
        ).observe(this, Observer {
            when (it) {
                is ApiResponse.Success -> StaticFunction.showSuccessAlert(activityContext)
                is ApiResponse.Progress -> showProgressBar(it.loading, dataBinding.progressBar)
                is ApiResponse.Failure -> logThis(it.errorMessage.message)
                is ApiResponse.ErrorCode -> logThis(it.errorCode.message)
            }
        })

    }

    private fun checkInputValidation(): Boolean {
        if (!StaticFunction.checkValidation(dataBinding.parcelNameET, dataBinding.parcelNameET.text, "পার্সেল নাম ? ")) return false
        if (!StaticFunction.checkValidation(dataBinding.parcelCompanyET, dataBinding.parcelCompanyET.text, "কৈ থেকে এসেছে ? ")) return false
        if (!StaticFunction.checkValidation(dataBinding.whereParcelCame, dataBinding.whereParcelCame.text, "কার কাছে এসেছে ? ")) return false
//        if (!StaticFunction.checkValidation(dataBinding.whereParcelCame, employeeData.id.toString(), "কার কাছে এসেছে ? ")) return false


        return true
    }

    override fun onPickResult(r: PickResult?) {
        if (r?.error == null) {

            dataBinding.userPhotoIV.setImageURI(null);

            mFileUri = r?.uri.toString();
            imageBitmap = r?.bitmap;

            dataBinding.userPhotoIV.setImageURI(r?.uri);

        } else {

            Toast.makeText(this, r.error.message, Toast.LENGTH_LONG).show();
        }

    }

    override fun onItemClick(employeeData: EmployeeListData) {
        alertDialog.dismiss()

        dataBinding.whereParcelCame.setText(employeeData.name.toString())

        this.employeeData = employeeData
    }
}