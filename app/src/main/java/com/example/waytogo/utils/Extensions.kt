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