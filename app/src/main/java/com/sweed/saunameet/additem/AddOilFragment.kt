package com.sweed.saunameet.additem

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sweed.saunameet.R
import com.sweed.saunameet.database.OilDatabase
import com.sweed.saunameet.databinding.AddOilFragmentBinding

class AddOilFragment : Fragment() {


    private lateinit var addOilViewModel: AddOilViewModel
    private lateinit var addOilViewModelFactory: AddOilViewModelFactory
    private lateinit var binding: AddOilFragmentBinding


    private val oilNameEditWatcher = object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            addOilViewModel.onChangeName(editable.toString())
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    private val oilRatingWatcher = object : RatingBar.OnRatingBarChangeListener{
        override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
            addOilViewModel.onChangeRating(rating)
        }

    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(
            inflater, R.layout.add_oil_fragment, container, false)

//        val arguments = SleepDetailFragmentArgs.fromBundle(arguments)

        val application = requireNotNull(this.activity).application
        val dataSource = OilDatabase.getInstance(application).oilDatabaseDao

        val addItemViewModelFactory = AddOilViewModelFactory(dataSource, application)



        // Get a reference to the ViewModel associated with this fragment.
        addOilViewModel =
            ViewModelProvider(
                this, addItemViewModelFactory).get(AddOilViewModel::class.java)


        binding.addOilViewModel = addOilViewModel
        binding.setLifecycleOwner(this)
        initialiseUIElements()

//      Navigate back to AddList
        addOilViewModel.addNewOilEvent.observe(viewLifecycleOwner, Observer { oil ->
            oil?.let {
                this.findNavController().navigate(AddOilFragmentDirections.actionAddNewItemToAllOilsFragment())
                addOilViewModel.doneNavigating()
            }

        })

        return binding.root
    }

    private fun initialiseUIElements() {
        binding.saunaNameEditText.addTextChangedListener(oilNameEditWatcher)
        binding.ratingBar.setOnRatingBarChangeListener(oilRatingWatcher)
    }


}