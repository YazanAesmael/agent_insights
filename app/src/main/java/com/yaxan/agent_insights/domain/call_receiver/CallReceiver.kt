package com.yaxan.agent_insights.domain.call_receiver

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.CallLog
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.yaxan.agent_insights.common.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CallReceiver : BroadcastReceiver() {

    companion object {
        private var lastProcessedTime = 0L
        private const val DEBOUNCE_THRESHOLD = 1500
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastProcessedTime < DEBOUNCE_THRESHOLD) return
        lastProcessedTime = currentTime

        val sharedPrefs = context?.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
        val msg = sharedPrefs?.getString(Constants.APP_STATUS, "Unavailable") ?: "Unavailable"
        val isActive = sharedPrefs?.getBoolean(Constants.IS_ACTIVE, false) ?: false

        if (isActive) {
            when (intent?.action) {
                TelephonyManager.ACTION_PHONE_STATE_CHANGED -> {
                    val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
                    if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        }
                    }
                    if (state == TelephonyManager.EXTRA_STATE_IDLE) {
                        context?.let { checkForMissedCall(it) }
                    }
                }
            }
        }
    }

    private fun checkForMissedCall(context: Context) {
        val sharedPrefs = context.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
        val msg = sharedPrefs.getString(Constants.APP_STATUS, "null") ?: "null"
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
            val cursor = context.contentResolver.query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC")
            if (cursor?.moveToFirst() == true) {
                val type = cursor.getInt(cursor.getColumnIndexOrThrow(CallLog.Calls.TYPE))
                if (type == CallLog.Calls.MISSED_TYPE) {
                    val missedNumber = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER))
                    sendSMS(context, missedNumber, msg)
                }
            }
            cursor?.close()
        }
    }

    private fun sendSMS(context: Context, phoneNumber: String, message: String) {
        val smsManager: SmsManager = context.getSystemService(SmsManager::class.java)
        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
    }

}