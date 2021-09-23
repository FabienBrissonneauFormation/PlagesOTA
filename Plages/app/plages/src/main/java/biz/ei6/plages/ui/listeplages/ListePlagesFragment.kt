package biz.ei6.plages.ui.listeplages

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import biz.ei6.plages.Plage
import biz.ei6.plages.databinding.ListePlagesFragmentBinding

class ListePlagesFragment : Fragment() {

    val TAG = "LISTE_PLAGES"

    companion object {
        fun newInstance() = ListePlagesFragment()
    }

    private lateinit var viewModel: PlagesViewModel

    private lateinit var binding : ListePlagesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListePlagesFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(requireActivity()).get(PlagesViewModel::class.java)

        val adaptateur = PlageRecyclerAdapter (requireActivity(), viewModel.lesPlages, viewModel.favoris)

        viewModel.lesPlagesData.observe(viewLifecycleOwner, {
            Log.d(TAG, "WSMODEL mise à jour de l'adaptateur")
            adaptateur.data = it
            adaptateur.notifyDataSetChanged()
        })

        viewModel.chargePlages(requireContext())


        val manager = LinearLayoutManager(requireActivity())

        binding.listePlages.layoutManager = manager
        binding.listePlages.adapter = adaptateur


        binding.allerVersNouvellePlage.setOnClickListener {
            val action =  ListePlagesFragmentDirections.actionListePlagesFragmentToCreationFragment()

            findNavController().navigate(action)
        }

        setFragmentResultListener("request_key") { requestKey: String, bundle: Bundle ->

            val result = bundle.getParcelable<Plage>("plage")!!

           viewModel.addPlage(requireContext(),result)

        }
    }

}