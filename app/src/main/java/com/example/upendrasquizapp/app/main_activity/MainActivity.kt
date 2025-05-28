package com.example.upendrasquizapp.app.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.upendrasquizapp.quiz_feature.presentation.screens.navigationDrawer.NavigationDrawer

import com.example.upendrasquizapp.ui.theme.UpendrasQuizAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            UpendrasQuizAppTheme {
                val navController = rememberNavController()
                NavigationDrawer(navController = navController)
            }
        }
    }
}

