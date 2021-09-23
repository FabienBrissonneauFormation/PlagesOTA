package biz.ei6.plages.ui.listeplages

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.RoomDatabase
import biz.ei6.plages.Plage

import biz.ei6.plages.services.PlagesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList


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
    val lesPlagesData = MutableLiveData<MutableList<Plage>>()
    var favoris : ArrayList<Boolean> = ArrayList<Boolean>()

    var lesPlages = mutableListOf<Plage>()
        get() = lesPlagesData.value ?: mutableListOf<Plage>()

    var db : PlagesDatabase? = null


    val TAG= "WS_MODEL"

    fun getInstance(contexte : Context) : PlagesDatabase {
        if(db == null)
        db = Room.databaseBuilder(contexte, PlagesDatabase::class.java, "plages").build()
        return db !!
    }

   fun chargePlages(contexte : Context) {

       viewModelScope.launch(Dispatchers.IO) {
           lesPlages = getInstance(contexte).plagesDao().selectPlages.toMutableList()
           lesPlagesData.postValue(lesPlages)
           repeat(lesPlages.size) { favoris.add(false) }
           Log.d(TAG, "plages size ${lesPlages.size}")
       }
   }

    fun addPlage(contexte: Context, plage : Plage) {

        // mise à jour locale des données
        lesPlagesData.value!!.add(plage)
        // mise en cohérence de la liste des booléens
        favoris.add(false)

        // provoquer la mise à jour du RecyclerView
        lesPlagesData.postValue(lesPlagesData.value)

        viewModelScope.launch(Dispatchers.IO) {
            getInstance(contexte).plagesDao().insertPlage(plage)
        }
    }

}