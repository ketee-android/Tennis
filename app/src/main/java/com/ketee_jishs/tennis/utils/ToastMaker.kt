package com.ketee_jishs.tennis.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

@Suppress("unused")
fun Activity.toast(context: Context, string: String?) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}

@Suppress("unused")
fun Fragment.toast(string: String?) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}