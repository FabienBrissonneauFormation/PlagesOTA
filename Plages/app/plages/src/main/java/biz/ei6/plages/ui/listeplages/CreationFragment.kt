package biz.ei6.plages.ui.listeplages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import biz.ei6.plages.Plage
import biz.ei6.plages.databinding.FragmentCreationBinding
import biz.ei6.plages.services.sauvegarde

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CreationFragment : Fragment() {


    lateinit var  binding : FragmentCreationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreationBinding.inflate(inflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.button.setOnClickListener {
            // Création d'un objet plage
            val plage = Plage(binding.nomCreationPlage.text.toString(),
                binding.descriptionCreationPlage.text.toString(),binding.imageCreationPlage.text.toString(),
                binding.urlCreationPlage.text.toString(),
                binding.largeurCreationPlage.text.toString().toInt(),
                binding.longueurCreationPlage.text.toString().toInt(),
                binding.latitudeCreationPlage.text.toString().toDouble(),
                binding.longueurCreationPlage.text.toString().toDouble())

            // Positionner le résultat
            setFragmentResult("request_key", bundleOf("plage" to plage))
            // Naviguer UP
            findNavController().navigateUp()
        }
    }

    fun onClick(v: View) {
        // fake
    }

}