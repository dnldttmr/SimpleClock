package de.dnldttmr.clock.ui.util

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View

fun Boolean.toReadableString(): String = if (this) "yes" else "no"

fun String.fromReadableString(): Boolean = this == "yes"

fun SharedPreferences.getReadableString(key: String): String = this.getString(key, false.toReadableString()) ?: false.toReadableString()

fun View.createBitmap(): Bitmap {
    this.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
    val bitmap = Bitmap.createBitmap(this.measuredWidth, this.measuredHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    this.layout(this.left, this.top, this.right, this.bottom)
    this.draw(canvas)
    return bitmap
}