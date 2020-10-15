package com.rokkhi.receptionistofficeapp.helper

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.rokkhi.receptionistofficeapp.helper.FontsOverride
import com.rokkhi.receptionistofficeapp.util.KeyFrame

class CustomBtn : AppCompatButton {
    constructor(context: Context) : super(context){
        applyCustomFont(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context,attrs){
        applyCustomFont(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr:Int) : super(context,attrs,defStyleAttr){
        applyCustomFont(context)
    }

    private fun applyCustomFont(context: Context) {
        val customFont = FontsOverride.getTypeface(KeyFrame.CUSTOM_REGULAR_FONT, context)
        typeface = customFont
    }
}