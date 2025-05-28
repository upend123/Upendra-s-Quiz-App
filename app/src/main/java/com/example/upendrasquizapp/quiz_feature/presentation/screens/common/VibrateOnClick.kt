package com.example.upendrasquizapp.quiz_feature.presentation.screens.common

import android.Manifest
import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresPermission
import androidx.compose.ui.platform.LocalContext



fun VibrateOnClick(context : Context){

    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    val vibrationEffect = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)

    vibrator.vibrate(vibrationEffect)

}