package biz.ei6.plages.ui.listeplages

import androidx.lifecycle.ViewModel
import biz.ei6.plages.Plage

class PlagesViewModel : ViewModel() {

    val p1 = Plage("La Palmyre Les Mathes",
        "La Palmyre est un quartier résidentiel et touristique dépendant de la commune des Mathes, dans la partie occidentale de la presqu'île d'Arvert, en Charente-Maritime.",
        "lapalmyre.jpg",
        50,
         1000,
        45.690962,-1.193728
    )
    val p2 = Plage("Kerler Fouesnant",
        "Plage familiale pas trop connue ! Idéale si on veut faire jouer les enfants dans le sable et la mer pas trop loin pour surveiller les enfants dans l eau..",
        "kerler.jpg",
        45,
        550,
        45.690962,-1.193728
    )
    val lesPlages = mutableListOf(p1,p2,p2,p2,p2)
    val favoris = mutableListOf(false,false,false,false,false)


}