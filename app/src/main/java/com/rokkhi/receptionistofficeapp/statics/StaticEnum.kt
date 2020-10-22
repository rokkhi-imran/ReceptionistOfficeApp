package com.rokkhi.receptionistofficeapp.statics

enum class EmployeeEntryStatus { INSIDE, OUTSIDE, UNAVAILABLE, AVAILABLE }
enum class ResponseStatus { SUCCESS, FAIL, LOADING }

enum class FolderName { PARCEL, VISITOR }

enum class EmployeeEntryStatus2(val status: String){
    INSIDE("INSIDE"),
    OUTSIDE("OUTSIDE"),
    AVAILABLE("AVAILABLE"),
    NOT_FOUND("NOT FOUND")
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

fun debugTest(){
//    Timber.e("enum to string value  EmployeeEntryStatus = ${EmployeeEntryStatus.INSIDE} , EmployeeEntryStatuss = ${EmployeeEntryStatus2.INSIDE}")
}