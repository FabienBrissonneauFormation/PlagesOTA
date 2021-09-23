package biz.ei6.plages


import android.content.BroadcastReceiver

import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {

    val TAG = "BRD SENDING"
    override fun onReceive(context: Context, intent: Intent) {

        Log.d(TAG, "envoi notif")
        context.sendOrderedBroadcast(Intent(PlageApplication.intentNotifName),null)

    }
}
