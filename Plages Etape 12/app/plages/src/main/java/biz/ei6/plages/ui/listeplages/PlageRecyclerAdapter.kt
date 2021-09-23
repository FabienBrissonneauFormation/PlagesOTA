package biz.ei6.plages.ui.listeplages

import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import biz.ei6.plages.Plage
import biz.ei6.plages.R
import biz.ei6.plages.databinding.ItemsPlageBinding
import java.io.IOException
import java.io.InputStream


class PlageRecyclerAdapter (val contexte : Context, var data : List<Plage>, val favoris : MutableList<Boolean>)
    : RecyclerView.Adapter<PlageRecyclerAdapter.MonViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonViewHolder {
        val layoutService = contexte.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val item = layoutService.inflate(R.layout.items_plage, parent, false)
        return MonViewHolder(ItemsPlageBinding.inflate(layoutService), item)
    }
    val TAG="WS_ADAPTATEUR"

    override fun onBindViewHolder(holder: MonViewHolder, position: Int) {

        Log.d(TAG, "BIND VIEW HOLDER $position")
        Log.d(TAG, "BIND VIEW HOLDER data size ${data.size}")
        Log.d(TAG, "BIND VIEW HOLDER favoris size ${favoris.size}")


        holder.nom.text = data[position].nom
        holder.description.text = data[position].description

        try {
            holder.image.setImageBitmap(getBitmapFromAsset(contexte, data[position].image))

        }
        catch(e : Exception) {

        }

        holder.estFavori.setOnClickListener {
            favoris[position] = !favoris[position]

        }

        holder.description.setOnClickListener {

            val action =  ListePlagesFragmentDirections.actionListePlagesFragmentToDetailFragment()
            action.plageId = position

            findNavController(holder.description).navigate(action)
        }
        Log.d(TAG, "BIND VIEW HOLDER FIN $position")

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MonViewHolder (binding: ItemsPlageBinding, itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nom : TextView
        val description : TextView
        val image : ImageView
        val estFavori : CheckBox

        init {
            nom = itemView.findViewById<TextView>(R.id.itemNom) // binding.itemNom//
            description = itemView.findViewById<TextView>(R.id.itemDescription)// binding.itemDescription //
            image = itemView.findViewById<ImageView>(R.id.itemImage)// binding.itemImage //
            estFavori =itemView.findViewById<CheckBox>(R.id.itemFavori) // binding.itemFavori //
        }
    }
    fun getBitmapFromAsset(context: Context, filePath: String): Bitmap {
        val assetManager: AssetManager = context.getAssets()
        val istr: InputStream

        return  try {
            istr = assetManager.open(filePath)
            BitmapFactory.decodeStream(istr)
        } catch (e: IOException) {
            throw e
        }
    }
}