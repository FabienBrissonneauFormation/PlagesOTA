package biz.ei6.plages.ui.listeplages

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biz.ei6.plages.Plage
import biz.ei6.plages.services.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class PlagesViewModel(application: Application) : AndroidViewModel(application) {

    val TAG = "WSMODEL"

    val lesPlagesData = MutableLiveData<ArrayList<Plage>>()
    var favoris : ArrayList<Boolean> = ArrayList<Boolean>()

    var lesPlages = mutableListOf<Plage>()
        get() = lesPlagesData.value ?: mutableListOf<Plage>()

    val webservice = RetrofitPlageDataSource(accesRetrofit())

    fun chargePlages(contexte: Context) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val dte = webservice.readAll()

                lesPlagesData.postValue(dte)
                favoris = ArrayList<Boolean> ().apply {repeat(dte.size) {add(false)}}

            } catch (e: Exception) {
                Log.d(TAG, "exception $e")
            }
        }


    }

    fun addPlage(contexte: Context, plage: Plage) {

        viewModelScope.launch (Dispatchers.IO){

            // appel au WS
            webservice.add(plage)

            // mise à jour locale des données
            lesPlagesData.value!!.add(plage)
            // mise en cohérence de la liste des booléens
            favoris.add(false)
            // provoquer la mise à jour du RecyclerView
            lesPlagesData.postValue(lesPlagesData.value)
        }

    }

}