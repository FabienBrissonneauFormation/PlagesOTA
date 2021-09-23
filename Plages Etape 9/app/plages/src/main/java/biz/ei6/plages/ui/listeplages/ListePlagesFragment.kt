package biz.ei6.plages.ui.listeplages

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import biz.ei6.plages.AlarmReceiver
import biz.ei6.plages.R
import biz.ei6.plages.databinding.ListePlagesFragmentBinding

class ListePlagesFragment : Fragment() {

    val TAG = "BRD ACTIVATE"

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


        }

        emettreNotification()
    }

    private fun emettreNotification() {

        val mNotif = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val ident = "Ce code marche pour API >= 27"
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channelName = "Plages nouvelles"
            val importance = NotificationManager.IMPORTANCE_LOW
            val notificationChannel = NotificationChannel(ident, channelName, importance)
            mNotif.createNotificationChannel(notificationChannel)
        }

        Log.d(TAG, "Enregistrement sur Alarme")
        val CODE_ALARME =42
        val intent = Intent(requireContext(), AlarmReceiver::class.java)
        val pi = PendingIntent.getBroadcast(requireActivity(),CODE_ALARME, intent, 0)

        val mgr = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        mgr.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000*60, pi)

    }
}