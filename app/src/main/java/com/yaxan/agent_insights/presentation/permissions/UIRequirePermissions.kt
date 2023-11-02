package com.yaxan.agent_insights.presentation.permissions

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun UIRequirePermissions(
    permissions: Array<String>,
    onPermissionGranted: @Composable () -> Unit,
    onPermissionDenied: @Composable (requester: () -> Unit) -> Unit
) {
    val context = LocalContext.current

    var grantState by remember {
        mutableStateOf(
            permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }
        )
    }

    if (grantState) onPermissionGranted()

    else {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { grantState = !it.containsValue(false) }
        )
        onPermissionDenied { launcher.launch(permissions) }
    }
}