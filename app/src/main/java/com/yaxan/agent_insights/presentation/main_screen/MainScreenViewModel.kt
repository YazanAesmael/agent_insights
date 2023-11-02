package com.yaxan.agent_insights.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaxan.agent_insights.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val status: StateFlow<String?> = repository.status

    private val _activationState = MutableStateFlow(false)
    val activationState: StateFlow<Boolean> = _activationState.asStateFlow()

    fun saveStatus(status: String) {
        viewModelScope.launch {
            repository.saveStatus(status)
            repository.getStatus()
        }
    }

    fun saveActivationState(isActive: Boolean) {
        viewModelScope.launch {
            repository.saveActivationState(isActive)
            _activationState.emit(repository.getActivationState())
        }
    }

    init {
        viewModelScope.launch {
            repository.getStatus()
            _activationState.emit(repository.getActivationState())
        }
    }

}
