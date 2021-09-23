package biz.ei6.plages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListePlageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_plage)

        val p = Plage("La Palmyre Les Mathes",
            "La Palmyre est un quartier résidentiel et touristique dépendant de la commune des Mathes, dans la partie occidentale de la presqu'île d'Arvert, en Charente-Maritime.",
             "lapalmyre.jpg"
            )

        val lesPlages = listOf(p,p,p,p,p)
        val favoris = mutableListOf(false,false,false,false,false)

        val adaptateur = PlageRecyclerAdapter (this, lesPlages, favoris)

        val manager = LinearLayoutManager(this)

        val recycler = findViewById<RecyclerView>(R.id.listePlages)

        recycler.layoutManager = manager
        recycler.adapter = adaptateur

    }
}