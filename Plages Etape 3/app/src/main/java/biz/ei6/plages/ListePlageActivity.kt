package biz.ei6.plages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import biz.ei6.plages.databinding.ActivityListePlageBinding

class ListePlageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListePlageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListePlageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val p = Plage("La Palmyre Les Mathes",
            "La Palmyre est un quartier résidentiel et touristique dépendant de la commune des Mathes, dans la partie occidentale de la presqu'île d'Arvert, en Charente-Maritime.",
             "lapalmyre.jpg"
            )

        val lesPlages = listOf(p,p,p,p,p)

        val adaptateur = PlageRecyclerAdapter (this, lesPlages)

        val manager = LinearLayoutManager(this)

        binding.listePlages.layoutManager = manager
        binding.listePlages.adapter = adaptateur

    }
}