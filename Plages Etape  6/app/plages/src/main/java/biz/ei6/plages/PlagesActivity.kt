package biz.ei6.plages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import biz.ei6.plages.ui.listeplages.ListePlagesFragment

class PlagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plages_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListePlagesFragment.newInstance())
                .commitNow()
        }
    }
}

