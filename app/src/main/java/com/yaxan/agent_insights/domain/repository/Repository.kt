package com.yaxan.agent_insights.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface Repository {

    val status: StateFlow<String?>

    suspend fun saveStatus(status: String)
    suspend fun getStatus()

    suspend fun saveActivationState(isActive: Boolean)
    suspend fun getActivationState(): Boolean
}