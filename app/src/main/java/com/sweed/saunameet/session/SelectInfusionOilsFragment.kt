package com.sweed.saunameet.session

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sweed.saunameet.R
import com.sweed.saunameet.databinding.SelectInfusionOilsFragmentBinding

class SelectInfusionOilsFragment : Fragment() {

    companion object {
        fun newInstance() = SelectInfusionOilsFragment()
    }

    private lateinit var selectInfusionOilsViewModel: SelectInfusionOilsViewModel
    private lateinit var binding: SelectInfusionOilsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.select_infusion_oils_fragment, container, false
        )
        selectInfusionOilsViewModel = ViewModelProvider(this).get(SelectInfusionOilsViewModel::class.java)

        selectInfusionOilsViewModel.onStartButtonEvent.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.i("SelectInfusionOilsFragment", "next!")
            }
        })
//      TODO remove hack for ViewModel failure
        binding.startInfusionsButton.setOnClickListener(selectInfusionOilsViewModel::onClickHaXX)

        return binding.root

    }


}