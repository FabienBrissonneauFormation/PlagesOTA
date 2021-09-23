package biz.ei6.plages

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import biz.ei6.plages.ui.listeplages.ListePlagesFragment
import org.osmdroid.config.Configuration
import java.io.File


private lateinit var appBarConfiguration: AppBarConfiguration

class PlagesActivity : AppCompatActivity() {

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


        // ce fichier est unique dans l'application
        val prefApp : SharedPreferences = getSharedPreferences("fichier_de_pref", Context.MODE_PRIVATE)

        // ce fichier est unique pour l'activité
        val prefAct : SharedPreferences = getPreferences( Context.MODE_PRIVATE)

        val valeur = "Nom"

        with(prefAct.edit()) {
            putString("clé", valeur)
            apply()
        }

        // lecture avec valeur par défaut
        val val1 = prefAct.getString("clé","Inconnu")

    }

        override fun onSupportNavigateUp(): Boolean {
            return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
                    || super.onSupportNavigateUp()
        }

    fun onClick(view: View) {}

}