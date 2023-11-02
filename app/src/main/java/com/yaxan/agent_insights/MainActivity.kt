package com.yaxan.agent_insights

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.yaxan.agent_insights.common.Constants.REQUEST_CODE
import com.yaxan.agent_insights.presentation.main_screen.MainScreen
import com.yaxan.agent_insights.ui.theme.Agent_insightsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Agent_insightsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val permissions = arrayOf(
                        android.Manifest.permission.READ_PHONE_STATE,
                        android.Manifest.permission.READ_CALL_LOG,
                        android.Manifest.permission.SEND_SMS,
                    )

                    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)
                    }else {
                        MainScreen()
                    }
                }
            }
        }
    }
}
