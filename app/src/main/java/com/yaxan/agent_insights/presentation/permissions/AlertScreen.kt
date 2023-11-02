package com.yaxan.agent_insights.presentation.permissions

import android.Manifest
import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat

val permissions = arrayOf(
    Manifest.permission.READ_PHONE_STATE,
    Manifest.permission.READ_CALL_LOG,
    Manifest.permission.SEND_SMS
)

@Composable
fun AlertScreen(requester: () -> Unit) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                ActivityCompat.requestPermissions(context as Activity, permissions, 22)
                requester()
            }
        ) {
            Icon(Icons.Rounded.Warning, "Permission Required")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Permission Required")
        }
    }
}