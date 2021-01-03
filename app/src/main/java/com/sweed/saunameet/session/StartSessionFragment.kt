package com.sweed.saunameet.session

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sweed.saunameet.IOnBackPressed
import com.sweed.saunameet.R
import com.sweed.saunameet.databinding.StartSessionFragmentBinding

class StartSessionFragment : Fragment(), IOnBackPressed {

    private lateinit var startSessionViewModel: StartSessionViewModel
    private lateinit var startSessionViewModelFactory: StartSessionViewModelFactory
    private lateinit var binding: StartSessionFragmentBinding

//    https://medium.com/@ffvanderlaan/fixing-the-dreaded-is-unknown-to-this-navcontroller-68c4003824ce

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(
            inflater, R.layout.start_session_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        startSessionViewModelFactory = StartSessionViewModelFactory(application)

        startSessionViewModel =
            ViewModelProvider(
                this, startSessionViewModelFactory
            ).get(StartSessionViewModel::class.java)

        startSessionViewModel.onNextButtonEvent.observe(viewLifecycleOwner, Observer {
            Log.i("onNextButtonEvent", "${this.findNavController()}")
            if (mayNavigate()){
                this.findNavController()
                    .navigate(StartSessionFragmentDirections.actionStartSessionFragmentToInfusionNumberSelectionFragment())
                startSessionViewModel.doneNavigating()
            }


        })

        binding.moodSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Write code to perform some action when progress is changed.
                startSessionViewModel.selectionChanged(seekBar.progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is stopped.
            }
        })

        binding.startSessionViewModel = startSessionViewModel
        binding.setLifecycleOwner(this)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }


    /**
     * Returns true if the navigation controller is still pointing at 'this' fragment, or false if it already navigated away.
     */
    fun Fragment.mayNavigate(): Boolean {

        val navController = findNavController()
        val destinationIdInNavController = navController.currentDestination?.id

        // add tag_navigation_destination_id to your ids.xml so that it's unique:
        val destinationIdOfThisFragment = view?.getTag(R.id.tag_navigation_destination_id) ?: destinationIdInNavController

        // check that the navigation graph is still in 'this' fragment, if not then the app already navigated:
        if (destinationIdInNavController == destinationIdOfThisFragment) {
            view?.setTag(R.id.tag_navigation_destination_id, destinationIdOfThisFragment)
            return true
        } else {
            Log.d("FragmentExtensions", "May not navigate: current destination is not the current fragment.")
            return false
        }
    }

    override fun onBackPressed(): Boolean {
        return mayNavigate()
    }


}