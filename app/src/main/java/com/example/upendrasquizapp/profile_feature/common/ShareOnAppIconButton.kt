package com.example.upendrasquizapp.profile_feature.common

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.upendrasquizapp.R

@Composable
fun ShareOnAppIconButton(
    icon: Painter,
    appPackage: String,
    message: String = "Hey, इस बेजोड़ ऐप को देखो!\nhttps://play.google.com/store/apps/details?id=${LocalContext.current.packageName}"
) {
    val context = LocalContext.current

    IconButton(onClick = {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
            setPackage(appPackage)                  // target the specific app
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        if (shareIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(shareIntent)
        } else {
            Toast.makeText(context, "${appPackage.substringAfterLast('.')} is not installed", Toast.LENGTH_SHORT).show()
        }
    }) {
        Icon(
            painter = icon,
            contentDescription = "Share via ${appPackage.substringAfterLast('.')}",
            modifier = Modifier.size(30.dp),
            tint = Color.Unspecified
        )
    }
}

fun isAppInstalled(context: Context, packageName: String): Boolean {
    return try {
        context.packageManager.getPackageInfo(packageName, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

@Composable
fun TelegramShareButton(
    icon: Painter,
    message: String = "Hey, इस बेहतरीन ऐप को देखो!\nhttps://play.google.com/store/apps/details?id=${LocalContext.current.packageName}"
) {
    val context = LocalContext.current
    IconButton(onClick = {
        // URL encode करना न भूलें
        val encodedText = Uri.encode(message)
        val telegramUri = Uri.parse("https://t.me/share/url?url=&text=$encodedText")

        val intent = Intent(Intent.ACTION_VIEW, telegramUri)
            .apply { setPackage("org.telegram.messenger") }

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Telegram is not installed", Toast.LENGTH_SHORT).show()
        }
    }) {
        Icon(
            painter = icon,
            contentDescription = "Share via Telegram",
            modifier = Modifier.size(30.dp),
            tint = Color.Unspecified
        )
    }
}

