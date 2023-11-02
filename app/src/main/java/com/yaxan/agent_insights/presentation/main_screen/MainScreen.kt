package com.yaxan.agent_insights.presentation.main_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yaxan.agent_insights.common.Constants.APP_HEADER

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    ContactStatusAppUI(mainScreenViewModel = viewModel)
}

@Composable
fun ContactStatusAppUI(mainScreenViewModel: MainScreenViewModel) {
    val status by mainScreenViewModel.status.collectAsState()
    val activationState by mainScreenViewModel.activationState.collectAsState()

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = APP_HEADER,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusInputField(status: String, onStatusChange: (String) -> Unit) {
    TextField(
        value = status,
        onValueChange = onStatusChange,
        label = { Text("Type Message...") },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun ActivationToggle(isActive: Boolean, onToggleChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = if (isActive) "Status Active" else "Status Inactive",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Switch(
            checked = isActive,
            onCheckedChange = onToggleChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}
