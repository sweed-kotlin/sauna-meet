package com.sweed.saunameet.allIOils

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sweed.saunameet.R
import com.sweed.saunameet.database.Oil
import com.sweed.saunameet.database.OilDatabase
import com.sweed.saunameet.database.OilDatabaseDao
import com.sweed.saunameet.databinding.AllOilsFragmentBinding

class AllOilsFragment : Fragment() {

    private lateinit var allOilsViewModel: AllOilsViewModel
    private lateinit var allOilsViewModelFactory: AllOilsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: AllOilsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.all_oils_fragment, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = OilDatabase.getInstance(application).oilDatabaseDao

        allOilsViewModelFactory = AllOilsViewModelFactory(dataSource, application)
        allOilsViewModel =
            ViewModelProvider(
                this, allOilsViewModelFactory
            ).get(AllOilsViewModel::class.java)


        binding.allOilsViewModel = allOilsViewModel
        binding.setLifecycleOwner(this)

        val adapter = AllOilListAdapter(OilItemListener { oilId ->
            this.findNavController().navigate(AllOilsFragmentDirections.actionAllOilsFragmentToAddNewItem())
        })

        allOilsViewModel.oils.observe(viewLifecycleOwner, Observer { oils ->
//            Log.i("AllOils" ,"${oils}")
            oils?.let {
                if (oils.isNullOrEmpty()) {
                    allOilsViewModel.initializeDatabase()
                } else {
                    adapter.submitList(oils)
                }
            } 
        })


        binding.allOilList.adapter = adapter

        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        binding.allOilList.layoutManager = manager

        return binding.root
    }
}