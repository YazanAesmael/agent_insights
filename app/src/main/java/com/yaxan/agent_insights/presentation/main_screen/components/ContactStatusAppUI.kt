package com.yaxan.agent_insights.presentation.main_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yaxan.agent_insights.common.Constants
import com.yaxan.agent_insights.presentation.main_screen.MainScreenViewModel

@Composable
fun ContactStatusAppUI(mainScreenViewModel: MainScreenViewModel) {
    val status by mainScreenViewModel.status.collectAsState()
    val activationState by mainScreenViewModel.activationState.collectAsState()

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = Constants.APP_HEADER,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(20.dp))
            StatusInputField(
                status = status ?: "null",
                onStatusChange = { newStatus -> mainScreenViewModel.saveStatus(newStatus) }
            )
            Spacer(modifier = Modifier.height(20.dp))
            ActivationToggle(
                isActive = activationState,
                onToggleChange = { active -> mainScreenViewModel.saveActivationState(active) }
            )
        }
    }
}