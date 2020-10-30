package com.rokkhi.receptionistofficeapp.util

object KeyFrame {
    const val imageUploadURL="https://kolig.api.rokkhi.com/api/v1/image/uploadSingle" //TODO image upload api need to use Rokkhi office API

    // private const server key
    const val KEY_INSIDE = "INSIDE"
    const val KEY_VISITOR_INSIDE = "INSIDE_COMPOUND"
    const val KEY_VISITOR_OUTSIDE = "OUTSIDE_COMPOUND"
    const val KEY_OUTSIDE = "OUTSIDE"
    const val KEY_PARCEL = "PARCEL"
    const val KEY_VISITOR = "VISITOR"
    const val RC_SIGN_IN = 12773

    //config key
    const val NOTIFICATION_SERVICE_ID: Int = 1011

    // shared-pref key frame

    const val USER_AUTH = "token"
    const val DEVICE_TOKEN = "device_token"
    const val PHONE_NUMBER = "phone_number"
    const val USER_ID = "user_id"
    const val USER_NAME = "username"
    const val BRANCH_ID = "branchId"
    const val COMPANY_ID = "companyId"
    const val DEPARTMENT_ID = "departmentId"


    //User Role Code
    const val RECEPTION_ROLE_CODE = "1001"
    const val USER_ROLE_CODE = "1000"



    //permission handler code
    const val READ_STORAGE_PERMISSION_REQUEST_CODE: Int = 941
    const val WRITE_EXTERNAL_STORAGE_REQUEST_CODE: Int = 942
    const val CAMERA_REQUEST_CODE: Int = 943
    const val ACCESS_FINE_LOCATION_REQUEST_CODE: Int = 944
    const val ACCESS_BACKGROUND_LOCATION_REQUEST_CODE: Int = 945
    const val ACCESS_COARSE_LOCATION_REQUEST_CODE: Int = 946

    //file handler
    const val FILE_PATH_PREFIX_FROM_CACHE: String = "/data/user/0/live.qtec.survey/cache/"

    //exceptions
    const val EXCEPTION_NO_ANSWER_DATA_FOUND: String = "No answer is given / data selected"

    //utils key
    const val CUSTOM_REGULAR_FONT = "fonts/OpenSans-Regular.ttf"
    const val CUSTOM_BOLD_FONT = "fonts/OpenSans-Bold.ttf"
    const val SOMETHING_WRONG = "Something went wrong!"
    const val TRY_AGAIN_LATER = "Try after some while!"
    const val LET_US_KNOW = "Please contact developer!"
    const val UPDATE_PROFILE = "Please update your profile first!"
    const val DATE_TIME_PATTERN = "dd/MM/yyyy hh:mm:ss aa"
    const val DATE_TIME_PATTERN_2 = "dd/MM/yyyy hh:mm aa"


}