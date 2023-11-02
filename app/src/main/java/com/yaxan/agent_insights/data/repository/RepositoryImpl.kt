package com.yaxan.agent_insights.data.repository

import android.content.SharedPreferences
import com.yaxan.agent_insights.common.Constants.APP_STATUS
import com.yaxan.agent_insights.common.Constants.IS_ACTIVE
import com.yaxan.agent_insights.domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RepositoryImpl(private val sharedPreferences: SharedPreferences) : Repository {

    private val _status = MutableStateFlow<String?>("")
    override val status: StateFlow<String?> = _status

    override suspend fun saveStatus(status: String) {
        sharedPreferences.edit().putString(APP_STATUS, status).apply()
    }

    override suspend fun getStatus() {
        val appStatus = sharedPreferences.getString(APP_STATUS, "")
        _status.emit(appStatus)
    }

    override suspend fun saveActivationState(isActive: Boolean) {
        sharedPreferences.edit().putBoolean(IS_ACTIVE, isActive).apply()
    }

    override suspend fun getActivationState(): Boolean {
        return sharedPreferences.getBoolean(IS_ACTIVE, false)
    }

}