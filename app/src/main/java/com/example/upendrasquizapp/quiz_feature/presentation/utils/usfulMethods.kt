package com.example.upendrasquizapp.quiz_feature.presentation.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun compressTheImage(data : ByteArray): ByteArray{

    val bitmap = BitmapFactory.decodeByteArray(data ,0,data.size)
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG,50,outputStream)
    return outputStream.toByteArray()
}


fun isValidBanner(data: ByteArray): Boolean {
    // बैनर के लिए थ्रेशोल्ड
    val BANNER_RATIO    = 3f       // width / height
    val RATIO_TOLERANCE = 0.1f     // ±0.1 ढील
    val MIN_WIDTH       = 1200
    val MIN_HEIGHT      = 400

    // सिर्फ डायमेंशन चेक (inJustDecodeBounds)
    val opts = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    BitmapFactory.decodeByteArray(data, 0, data.size, opts)
    val w = opts.outWidth
    val h = opts.outHeight
    val ratio = w.toFloat() / h

    if (w < MIN_WIDTH || h < MIN_HEIGHT) {
        // 너무 छोटा
        return false
    }
    if (kotlin.math.abs(ratio - BANNER_RATIO) > RATIO_TOLERANCE) {
        // रेशियो ठीक नहीं
        return false
    }
    return true
}



fun isValidProfile(data: ByteArray): Boolean {
    // Profile thresholds
    val PROFILE_RATIO      = 1f
    val RATIO_TOLERANCE    = 0.1f
    val PROFILE_MIN_DIM    = 300
    val PROFILE_MAX_DIM    = 1024

    // सिर्फ डायमेंशन चेक (inJustDecodeBounds = true)
    val opts = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    BitmapFactory.decodeByteArray(data, 0, data.size, opts)
    val w = opts.outWidth
    val h = opts.outHeight
    val ratio = w.toFloat() / h

    // मिनिमम/मैक्सिमम डायमेंशन चेक
    if (w < PROFILE_MIN_DIM || h < PROFILE_MIN_DIM) return false
    if (w > PROFILE_MAX_DIM || h > PROFILE_MAX_DIM) return false

    // रेशियो चेक
        if (kotlin.math.abs(ratio - PROFILE_RATIO) > RATIO_TOLERANCE) return false

    return true
}

