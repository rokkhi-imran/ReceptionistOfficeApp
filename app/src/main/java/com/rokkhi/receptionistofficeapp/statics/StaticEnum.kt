package com.rokkhi.receptionistofficeapp.statics

enum class EmployeeEntryStatus { INSIDE, OUTSIDE, UNAVAILABLE, AVAILABLE }
enum class VisitorStatus { IN, OUT, PENDING_PERMISSION, REJECTED, ALLOWED, ERROR }
enum class ResponseStatus { SUCCESS, FAIL, LOADING }
enum class FolderName { PARCEL, VISITOR }

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}