package biz.ei6.plages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListePlageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_plage)

        val p = Plage("La Palmyre Les Mathes",
            "La Palmyre est un quartier résidentiel et touristique dépendant de la commune des Mathes, dans la partie occidentale de la presqu'île d'Arvert, en Charente-Maritime.",
             "lapalmyre.jpg",0,0,0.0,0.0
            )

        val lesPlages = listOf(p,p,p,p,p)
        val favoris = mutableListOf(false,false,false,false,false)

        val adaptateur = PlageRecyclerAdapter (this, lesPlages, favoris)

        val manager = LinearLayoutManager(this)

        val recycler = findViewById<RecyclerView>(R.id.listePlages)

        recycler.layoutManager = manager
        recycler.adapter = adaptateur

        val menu = findViewById<Toolbar>(R.id.listeToolbar)
        setSupportActionBar(menu)

        val btnPlages = findViewById<Button>(R.id.allerVersListePlages)

        btnPlages.isEnabled = false

        val btnCartes = findViewById<Button>(R.id.allerVersCartePlages)

        btnCartes.setOnClickListener { allerVersCarte()}
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        if( item.itemId == R.id.menu_nouvelle_plage) {
            // ouvrir le nouvel écran
            Toast.makeText(this,"Création d'une nouvelle plage", Toast.LENGTH_LONG).show();

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu : Menu)  : Boolean{
        menuInflater.inflate(R.menu.principal, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun allerVersCarte() {
        Toast.makeText(this, "Nous passons à la carte des plages", Toast.LENGTH_LONG).show()
    }

}