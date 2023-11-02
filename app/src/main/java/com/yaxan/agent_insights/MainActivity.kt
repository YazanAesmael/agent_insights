package com.yaxan.agent_insights

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.yaxan.agent_insights.presentation.main_screen.MainScreen
import com.yaxan.agent_insights.presentation.permissions.AlertScreen
import com.yaxan.agent_insights.presentation.permissions.UIRequirePermissions
import com.yaxan.agent_insights.presentation.permissions.permissions
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
                    UIRequirePermissions(
                        permissions = permissions,
                        onPermissionGranted = { MainScreen() },
                        onPermissionDenied = { AlertScreen(it) }
                    )
                }
            }
        }
    }
}



