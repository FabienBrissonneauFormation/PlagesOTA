package biz.ei6.plages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import biz.ei6.plages.databinding.ActivityMainBinding
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val btnPlages = findViewById<Button>(R.id.allerVersListePlages)
        val btnCartes = findViewById<Button>(R.id.allerVersCartePlages)


        binding.allerVersListePlages.setOnClickListener { allerVersListe()}

        binding.allerVersCartePlages.setOnClickListener { allerVersCarte()}


    }

    private fun allerVersCarte() {
        longToast("Nous passons à la carte des plages")
    }

    private fun allerVersListe() {
        Toast.makeText(this, "Nous passons à la liste des plages", Toast.LENGTH_LONG).show()
    }
}