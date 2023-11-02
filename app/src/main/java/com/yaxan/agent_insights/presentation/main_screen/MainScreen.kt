package com.yaxan.agent_insights.presentation.main_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.yaxan.agent_insights.presentation.main_screen.components.ContactStatusAppUI

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {
    ContactStatusAppUI(mainScreenViewModel = viewModel)
}






