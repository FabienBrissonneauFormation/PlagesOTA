package biz.ei6.plages

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat


class NotificationReceiver : BroadcastReceiver() {
val TAG=" BRD Receiver"
    override fun onReceive(context: Context, intent: Intent) {

        Log.d(TAG,"Notification en cours")
        val mNotif = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val ala = Intent(context, PlagesActivity::class.java)
        ala.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pi = PendingIntent.getActivity(context, 0, ala, PendingIntent.FLAG_UPDATE_CURRENT)

        val ident = "Ce code marche pour API >= 27"
        val bld = NotificationCompat.Builder(context, ident)
            .setSmallIcon(R.drawable.ic_notif)
            .setContentTitle("Attention !")
            .setContentText("La liste des plages est disponible !")
            .setContentIntent(pi)

        val note = bld.build()

        mNotif.notify(1, note)
    }
}
