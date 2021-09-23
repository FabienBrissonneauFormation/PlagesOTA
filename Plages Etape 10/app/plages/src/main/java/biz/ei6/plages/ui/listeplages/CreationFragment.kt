package biz.ei6.plages.ui.listeplages

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import biz.ei6.plages.Plage
import biz.ei6.plages.PlagesActivity
import biz.ei6.plages.R
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

    override fun onViewCreated(view : View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

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

    private fun emettreNotification() {

        val mNotif = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val ala = Intent(requireActivity(), PlagesActivity::class.java)
        ala.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pi = PendingIntent.getActivity(requireActivity(), 0, ala, PendingIntent.FLAG_UPDATE_CURRENT)

        val ident = "utile pour API 27"
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channelName = "Ce code marche pour API >= 27"
            val importance = NotificationManager.IMPORTANCE_LOW
            val notificationChannel = NotificationChannel(ident, channelName, importance)
            mNotif.createNotificationChannel(notificationChannel)
        }

        val bld = NotificationCompat.Builder(requireActivity(), ident)
            .setSmallIcon(R.drawable.ic_notif)
            .setContentTitle("Attention !")
            .setContentText("Un nouvel anniversaire est ajouté")
            .setContentIntent(pi)

        val note = bld.build()

        mNotif.notify(1, note)
    }

    fun onClick(v: View) {
        // fake
    }

}