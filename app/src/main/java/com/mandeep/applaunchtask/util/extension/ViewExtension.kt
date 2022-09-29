package com.mandeep.applaunchtask.util.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*
import java.math.RoundingMode
import java.text.DecimalFormat



infix fun View.onClick(click: () -> Unit) {
    setOnClickListener { click() }
}

fun View.disabledForSec(milliSeconds: Long = 1000) {
    isEnabled = false
    CoroutineScope(Dispatchers.IO).launch {
        delay(milliSeconds)
        withContext(Dispatchers.Main) {
            isEnabled = true
        }
    }
}

infix fun TextInputLayout.showErrorMessage(message: String) {
    isEnabled = false
    error = message
    CoroutineScope(Dispatchers.IO).launch {
        delay(750)
        withContext(Dispatchers.Main) {
            isEnabled = true
            error = null
        }
    }
}

infix fun View.showMessage(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}


infix fun Any.toCelsius(data: Double): String {
    return "" + DecimalFormat("###0").apply {
        roundingMode = RoundingMode.CEILING
    }.format(data) + " \u2103"
}

infix fun Any.presureHpa(data: Double): String {
    return "" + DecimalFormat("###0").apply {
        roundingMode = RoundingMode.CEILING
    }.format(data) + " hPa"
}

infix fun Any.convertWindSpeed(data: Double): String {
    return "" + DecimalFormat("###0.0").apply {
        roundingMode = RoundingMode.CEILING
    }.format(data * 3.6) + " km/h"
}

infix fun Any.convertVisibilty(data: Double): String {
    return "" + DecimalFormat("###0.0").apply {
        roundingMode = RoundingMode.CEILING
    }.format(data / 1000) + " km"
}

infix fun Any.convertHumidity(data: Double): String {
    return "" + DecimalFormat("###0.0").apply {
        roundingMode = RoundingMode.CEILING
    }.format(data) + " %"
}

infix fun Any.generateImgUrl(code: String): String {
    return "https://openweathermap.org/img/w/$code.png"
}

fun View.visible() {
    visibility = View.VISIBLE
}
fun View.gone() {
    visibility = View.GONE

}