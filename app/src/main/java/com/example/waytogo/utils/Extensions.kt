package com.example.waytogo.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

// EditText에 대한 익스텐션
fun EditText.onMyTextChanged(completion : (CharSequence?) -> Unit){
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            completion(s)
        }

        override fun afterTextChanged(s: Editable?) {
        }
    })
}

// 문자열이 json 형태 인지, json 배열 형태인지
fun String?.isJsonObject():Boolean {
    return this?.startsWith("{") == true && this.endsWith("}")
}

// 문자열이 Json 배열인지
fun String?.isJsonArray():Boolean {
    return this?.startsWith("[") == true && this.endsWith("]")
}