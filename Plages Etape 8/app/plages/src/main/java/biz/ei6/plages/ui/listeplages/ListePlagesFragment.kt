package biz.ei6.plages.ui.listeplages

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import biz.ei6.plages.R
import biz.ei6.plages.databinding.ListePlagesFragmentBinding

class ListePlagesFragment : Fragment() {

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
        //return inflater.inflate(R.layout.liste_plages_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlagesViewModel::class.java)

        val adaptateur = PlageRecyclerAdapter (requireActivity(), viewModel.lesPlages, viewModel.favoris)

        val manager = LinearLayoutManager(requireActivity())

        binding.listePlages.layoutManager = manager
        binding.listePlages.adapter = adaptateur


        setFragmentResultListener("request_key") { requestKey: String, bundle: Bundle ->
            val result = bundle.getString("your_data_key")
            // do something with the result
        }
    }

}