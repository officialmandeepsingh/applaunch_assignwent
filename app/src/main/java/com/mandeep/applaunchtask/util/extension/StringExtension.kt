package com.mandeep.applaunchtask.util.extension

import android.text.Editable
import androidx.core.util.PatternsCompat




fun String?.isValid(): Boolean {
    return this != null && this.isNotEmpty() && this.isNotBlank()
}

fun String?.isValidPassword(): Boolean {
    this?.let {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
        val passwordMatcher = Regex(passwordPattern)
        return passwordMatcher.find(it) != null
    } ?: return false
}

fun String?.isNotValid(): Boolean {
    return !this.isValid()
}


private val emailRegex = PatternsCompat.EMAIL_ADDRESS

fun String?.isValidName(): Boolean {
    return isValid() && this?.length?:"".length >=3


}

fun String?.isEmail(): Boolean {
    var valid = false
    this?.let { email ->
        valid =
            email.isValid() && emailRegex.matcher(email.subSequence(0, email.length)).matches() && (
                    email.endsWith(".com") || email.endsWith(".in") || email.endsWith(".edu") ||
                            email.endsWith(".org") || email.endsWith(".net"))
    }
    return valid
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
