package biz.ei6.plages

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import biz.ei6.plages.ui.listeplages.ListePlagesFragment
import org.osmdroid.config.Configuration
import java.io.File


private lateinit var appBarConfiguration: AppBarConfiguration

class PlagesActivity : AppCompatActivity() {

    val TAG = "BRD Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plages_activity)

        val osmConf = Configuration.getInstance()
        val basePath = File(getCacheDir().getAbsolutePath(), "osmdroid")
        osmConf.osmdroidBasePath = basePath
        val tileCache = File(osmConf.osmdroidBasePath.absolutePath, "tile")
        osmConf.osmdroidTileCache = tileCache
        osmConf.setUserAgentValue(packageName)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

        override fun onSupportNavigateUp(): Boolean {
            return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
                    || super.onSupportNavigateUp()
        }

    // Gestion du premier plan pour l'activité

    val notifFilter = IntentFilter(PlageApplication.intentNotifName).apply { priority=6 }

    val topReceiver = object : BroadcastReceiver() {
        override fun onReceive(context : Context?, intent : Intent?) {
            Log.d(TAG,"Message reçu par broadcast")
            abortBroadcast()
        }
    }

    override fun onStart() {

        super.onStart()

        this.registerReceiver(topReceiver,notifFilter)
    }

    override fun onStop() {

        this.unregisterReceiver(topReceiver)

        super.onStop()
    }
}