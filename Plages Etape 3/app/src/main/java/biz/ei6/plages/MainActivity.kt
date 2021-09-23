package biz.ei6.plages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlages = findViewById<Button>(R.id.allerVersListePlages)

        btnPlages.setOnClickListener { allerVersListe()}

        val btnCartes = findViewById<Button>(R.id.allerVersCartePlages)

        btnCartes.setOnClickListener { allerVersCarte()}
    }

    private fun allerVersCarte() {
        Toast.makeText(this, "Nous passons à la carte des plages", Toast.LENGTH_LONG).show()
    }

    private fun allerVersListe() {
        Toast.makeText(this, "Nous passons à la liste des plages", Toast.LENGTH_LONG).show()
    }
}