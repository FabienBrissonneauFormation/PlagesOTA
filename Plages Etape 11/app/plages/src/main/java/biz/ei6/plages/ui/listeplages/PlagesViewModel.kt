package biz.ei6.plages.ui.listeplages

import android.content.Context
import androidx.lifecycle.ViewModel
import biz.ei6.plages.Plage
import biz.ei6.plages.services.BaseService
import biz.ei6.plages.services.charge
import biz.ei6.plages.services.sauvegarde

class PlagesViewModel : ViewModel() {

    val p1 = Plage("La Palmyre Les Mathes",
        "La Palmyre est un quartier résidentiel et touristique dépendant de la commune des Mathes, dans la partie occidentale de la presqu'île d'Arvert, en Charente-Maritime.",
        "lapalmyre.jpg",
        "",
        50,
         1000,
        45.690962,-1.193728
    )
    val p2 = Plage("Kerler Fouesnant",
        "Plage familiale pas trop connue ! Idéale si on veut faire jouer les enfants dans le sable et la mer pas trop loin pour surveiller les enfants dans l eau..",
        "kerler.jpg",
        "",
        45,
        550,
        45.690962,-1.193728
    )
    var lesPlages = mutableListOf<Plage>()
    val favoris = mutableListOf<Boolean>()


   fun chargePlages(contexte : Context) {
       lesPlages = BaseService(contexte).query()
       //charge(contexte)

       repeat(lesPlages.size) { favoris.add(false)}
   }

    fun addPlage(contexte: Context, plage : Plage) {
        BaseService(contexte).insert(plage)
        //sauvegarde(contexte,plage)
        lesPlages.add(plage)
        favoris.add(false);
    }

}