package biz.ei6.plages.ui.listeplages

import androidx.lifecycle.ViewModel
import biz.ei6.plages.Plage

class PlagesViewModel : ViewModel() {

    val p = Plage("La Palmyre Les Mathes",
        "La Palmyre est un quartier résidentiel et touristique dépendant de la commune des Mathes, dans la partie occidentale de la presqu'île d'Arvert, en Charente-Maritime.",
        "lapalmyre.jpg"
    )

    val lesPlages = listOf(p,p,p,p,p)
    val favoris = mutableListOf(false,false,false,false,false)

}