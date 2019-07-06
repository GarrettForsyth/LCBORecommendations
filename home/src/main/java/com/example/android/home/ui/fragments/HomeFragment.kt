package com.example.android.home.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.android.core.EventObserver
import com.example.android.core.di.Injectable
import com.example.android.home.R
import com.example.android.home.databinding.FragmentHomeBinding
import com.example.android.home.ui.viewmodels.HomeViewModel
import com.example.android.lcborecommendations.test.OpenForTesting
import javax.inject.Inject


/**
 * Fragment for the home screen. This screen lets the user navigate to other features
 * and is the landing fragment for the app.
 */
@OpenForTesting
class HomeFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: HomeViewModel

    lateinit var binding: FragmentHomeBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupNavigation()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.homeViewModel = viewModel

        return binding.root
    }

    private fun setupNavigation() {
        viewModel.browseCommand.observe(this, EventObserver {
            val action = HomeFragmentDirections.actionHomeToBrowse()
            navController().navigate(action)
        })
    }

    fun navController() = findNavController()


}
