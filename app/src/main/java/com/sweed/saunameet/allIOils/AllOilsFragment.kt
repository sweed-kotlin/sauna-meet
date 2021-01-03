package com.sweed.saunameet.allIOils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sweed.saunameet.R
import com.sweed.saunameet.database.OilDatabase
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

        val adapter = AllOilListAdapter(OilItemListener { oil ->
            if (oil.isAddButton) {
                this.findNavController().navigate(AllOilsFragmentDirections.actionAllOilsFragmentToAddNewItem())
            } else {
//                here i need to transfer my oilitem to the detail view
                allOilsViewModel.onOilClicked(oil.oilId)
            }
        })

        val favoAdapter = AllOilListAdapter(OilItemListener { oil ->
            if (oil.isAddButton) {
                this.findNavController().navigate(AllOilsFragmentDirections.actionAllOilsFragmentToAddNewItem())
            } else {
//                here i need to transfer my oilitem to the detail view
                allOilsViewModel.onOilClicked(oil.oilId)
            }
        })

        val recentlyAdapter = AllOilListAdapter(OilItemListener { oil ->
            if (oil.isAddButton) {
                this.findNavController().navigate(AllOilsFragmentDirections.actionAllOilsFragmentToAddNewItem())
            } else {
//                here i need to transfer my oilitem to the detail view
                allOilsViewModel.onOilClicked(oil.oilId)
            }
        })

        allOilsViewModel.oils.observe(viewLifecycleOwner, Observer { oils ->
            oils?.let {
                if (oils.isNullOrEmpty()) {
                    allOilsViewModel.initializeDatabase()
                } else {
                    adapter.submitList(oils)
                    favoAdapter.submitList(oils.filter { it.favorite && !it.isAddButton})
                    recentlyAdapter.submitList(oils.filter { it.lastUsedMillis != null })
                }
            }
        })

        allOilsViewModel.navigateToOilDetail.observe(viewLifecycleOwner, Observer { oilId ->
            oilId?.let {
                this.findNavController().navigate(AllOilsFragmentDirections.actionAllOilsFragmentToOilDetails(oilId))
                allOilsViewModel.onOilDetailNavigated()
            }
        })


        binding.allOilList.adapter = adapter
        binding.favoriteOilList.adapter = favoAdapter
        binding.recentlyUsedOils.adapter = recentlyAdapter


        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        val managerFavo = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        val managerRecycler = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)

        binding.allOilList.layoutManager = manager
        binding.favoriteOilList.layoutManager = managerFavo
        binding.recentlyUsedOils.layoutManager = managerRecycler


        return binding.root
    }
}