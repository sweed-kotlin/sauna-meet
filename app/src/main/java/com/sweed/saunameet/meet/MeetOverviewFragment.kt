package com.sweed.saunameet.meet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sweed.saunameet.R

class MeetOverviewFragment : Fragment() {

    companion object {
        fun newInstance() = MeetOverviewFragment()
    }

    private lateinit var viewModel: MeetOverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.meet_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MeetOverviewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}