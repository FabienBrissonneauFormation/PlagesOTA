package biz.ei6.plages.ui.listeplages

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import biz.ei6.plages.databinding.FragmentDetailBinding
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.ScaleBarOverlay
import java.io.IOException
import java.io.InputStream


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    private var plageId: Int = -1
    private val TAG = "Detail de plage "

    val args: DetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentDetailBinding

    private lateinit var viewModel: PlagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        plageId = args.plageId

        Log.d(TAG, "l'id de plage est $plageId")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlagesViewModel::class.java)

        binding.titrePlage.text = viewModel.lesPlages[plageId].nom
        binding.textePlage.text = viewModel.lesPlages[plageId].description
        binding.largeurPlage.text = viewModel.lesPlages[plageId].largeur.toString() + "m"
        binding.longueurPlage.text = viewModel.lesPlages[plageId].longueur.toString() + "m"


        val startPoint =
            GeoPoint(viewModel.lesPlages[plageId].latitude, viewModel.lesPlages[plageId].longitude)
        binding.imageMapPlage.setUseDataConnection(true)
        binding.imageMapPlage.zoomController.setZoomInEnabled(true)
        binding.imageMapPlage.zoomController.setZoomOutEnabled(true)
        binding.imageMapPlage.setMultiTouchControls(true)

        binding.imageMapPlage.getController().setCenter(startPoint)
        binding.imageMapPlage.getController().setZoom(13.5)

        val myScaleBarOverlay = ScaleBarOverlay(binding.imageMapPlage)
        binding.imageMapPlage.getOverlays().add(myScaleBarOverlay)

        val startMarker = Marker(binding.imageMapPlage)
        startMarker.position = startPoint
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        binding.imageMapPlage.getOverlays().add(startMarker)
    }

    fun getBitmapFromAsset(context: Context, filePath: String): Bitmap {
        val assetManager: AssetManager = context.getAssets()
        val istr: InputStream

        return try {
            istr = assetManager.open(filePath)
            BitmapFactory.decodeStream(istr)
        } catch (e: IOException) {
            throw e
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}