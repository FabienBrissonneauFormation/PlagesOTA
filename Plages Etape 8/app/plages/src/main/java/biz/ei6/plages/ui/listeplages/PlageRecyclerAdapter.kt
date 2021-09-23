package biz.ei6.plages.ui.listeplages

import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
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


class PlageRecyclerAdapter (val contexte : Context, val data : List<Plage>, val favoris : MutableList<Boolean>) : RecyclerView.Adapter<PlageRecyclerAdapter.MonViewHolder>(){

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonViewHolder {
        val layoutService = contexte.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val item = layoutService.inflate(R.layout.items_plage, parent, false)
        return MonViewHolder(ItemsPlageBinding.inflate(layoutService), item)
    }

    override fun onBindViewHolder(holder: MonViewHolder, position: Int) {
        holder.nom.text = data[position].nom
        holder.description.text = data[position].description
        holder.image.setImageBitmap(getBitmapFromAsset(contexte,data[position].image))

        holder.estFavori.setOnClickListener {
            favoris[position] = !favoris[position]

        }

        holder.description.setOnClickListener {
           /* val intent = Intent(contexte, DetailPlageActivity::class.java)
            intent.putExtra("position",position)
            startActivity(contexte, intent,null)*/

            /*
            val bundle = bundleOf("plageId" to position)

            findNavController(holder.description).navigate(R.id.action_listePlagesFragment_to_detailFragment, bundle)
*/
            val action =  ListePlagesFragmentDirections.actionListePlagesFragmentToDetailFragment()
            action.plageId = position

            findNavController(holder.description).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return data.size
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