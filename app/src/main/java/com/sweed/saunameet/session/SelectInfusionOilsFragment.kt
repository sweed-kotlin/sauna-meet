package com.sweed.saunameet.session

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sweed.saunameet.R

class SelectInfusionOilsFragment : Fragment() {

    companion object {
        fun newInstance() = SelectInfusionOilsFragment()
    }

    private lateinit var viewModel: SelectInfusionOilsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.select_infusion_oils_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SelectInfusionOilsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}