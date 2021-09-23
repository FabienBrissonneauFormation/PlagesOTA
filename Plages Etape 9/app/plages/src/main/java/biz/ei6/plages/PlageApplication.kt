package biz.ei6.plages

import android.app.Application
import android.content.IntentFilter

class PlageApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        val notifFilter = IntentFilter(intentNotifName)
        this.registerReceiver(notifReceiver,notifFilter)
    }

    override fun onTerminate() {

        this.unregisterReceiver(notifReceiver)
        super.onTerminate()
    }

    val notifReceiver = NotificationReceiver()

    companion object {
        val intentNotifName = "Plages.LISTE"
    }
}