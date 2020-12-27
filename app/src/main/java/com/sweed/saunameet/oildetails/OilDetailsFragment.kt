package com.sweed.saunameet.oildetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sweed.saunameet.R
import com.sweed.saunameet.database.OilDatabase
import com.sweed.saunameet.databinding.OilDetailsFragmentBinding

class OilDetailsFragment : Fragment() {


    private lateinit var oilDetailsViewModel: OilDetailsViewModel
    private lateinit var oilDetailsViewModelFactory: OilDetailsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: OilDetailsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.oil_details_fragment, container, false
        )


        val arguments = OilDetailsFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val dataSource = OilDatabase.getInstance(application).oilDatabaseDao

        oilDetailsViewModelFactory = OilDetailsViewModelFactory(dataSource, arguments.oilKey)
        oilDetailsViewModel =
            ViewModelProvider(
                this, oilDetailsViewModelFactory
            ).get(OilDetailsViewModel::class.java)


        oilDetailsViewModel.onBackToOverviewEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(OilDetailsFragmentDirections.actionOilDetailsToAllOilsFragment())
                oilDetailsViewModel.onFinishBackToOverview()
            }
        })
        oilDetailsViewModel.onDeleteEvent.observe(viewLifecycleOwner, Observer { oil ->
            if (oil != null) {
                this.findNavController().navigate(OilDetailsFragmentDirections.actionOilDetailsToAllOilsFragment())
                Toast.makeText(
                    application.applicationContext, "Deleted ${oil.name} from Oil-List",
                    Toast.LENGTH_LONG
                ).show();
                oilDetailsViewModel.onFinishDelete()
            }
        })

        binding.oilDetailsViewModel = oilDetailsViewModel
        binding.setLifecycleOwner(this)

        return binding.root


    }


}