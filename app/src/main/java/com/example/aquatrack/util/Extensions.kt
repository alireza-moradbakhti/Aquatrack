package com.example.aquatrack.util

import android.content.Context
import android.widget.Toast

fun Context.showMessage(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}