package com.example.aquatrack.util

import android.content.Context
import android.util.Patterns
import android.widget.Toast

/** show message in toast
 * @param text: String
 * @return Unit
 */
fun Context.showMessage(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

/** check if password and confirm password are the same
 * return true if they are the same
 * return false if they are not the same
 **/
fun passwordMatch(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}

/**
 * check if email is valid (using regex)
 * return true if it is valid
 * return false if it is not valid
 **/
fun String.checkEmailRegex(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}