package com.sweed.saunameet.session

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sweed.saunameet.R
import com.sweed.saunameet.additem.AddOilFragmentDirections
import com.sweed.saunameet.databinding.InfusionNumberSelectionFragmentBinding
import com.sweed.saunameet.databinding.OilDetailsFragmentBinding


class InfusionNumberSelectionFragment : Fragment() {



    private lateinit var infusionNumberSelectionViewModel: InfusionNumberSelectionViewModel
    private lateinit var infusionNumberSelectionViewModelFactory: InfusionNumberSelectionViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: InfusionNumberSelectionFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.infusion_number_selection_fragment, container, false
        )

        infusionNumberSelectionViewModelFactory = InfusionNumberSelectionViewModelFactory()

        infusionNumberSelectionViewModel = ViewModelProvider(this, infusionNumberSelectionViewModelFactory).get(InfusionNumberSelectionViewModel::class.java)

//        Todo Strange Bug, onClick never calls ViewModel method?!
//        infusionNumberSelectionViewModel.onNextButtonEvent.observe(viewLifecycleOwner, Observer {
//
//            if (it == true) {
//                this.findNavController()
//                    .navigate(InfusionNumberSelectionFragmentDirections.actionInfusionNumberSelectionFragmentToSelectInfusionOilsFragment())
//                infusionNumberSelectionViewModel.doneNavigating()
//            }
//        })

        binding.continueToSelectOilsButton.setOnClickListener {
            this.findNavController()
                .navigate(InfusionNumberSelectionFragmentDirections.actionInfusionNumberSelectionFragmentToSelectInfusionOilsFragment())
            infusionNumberSelectionViewModel.doneNavigating()
        }

//        val np: NumberPicker = binding.numberPicker
//
//        np.minValue = 1
//        np.maxValue = 10
//
//        np.setOnValueChangedListener { numberPicker: NumberPicker, i: Int, i1: Int ->
//            Toast.makeText(context,
//                "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT).show()
//        };
        return binding.root
    }

    fun onClick(){

    }

}