package com.example.android.browse.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.android.browse.R
import com.example.android.browse.databinding.FragmentBrowseBinding
import com.example.android.browse.ui.viewmodels.BrowseViewModel
import com.example.android.core.di.Injectable
import com.example.android.lcborecommendations.test.OpenForTesting
import com.google.android.material.bottomsheet.BottomSheetBehavior
import javax.inject.Inject

/**
 * Fragment representing the browse screen.
 */
@OpenForTesting
class BrowseFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: BrowseViewModel

    lateinit var binding: FragmentBrowseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BrowseViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_browse,
            container,
            false
        )
        binding.browseViewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.searchResults.observe(this, Observer {
            binding.browseSearchResultsList.adapter
        })
        observeBottomSheetVisibilityState()
    }

    private fun observeBottomSheetVisibilityState() {
        viewModel.sortOptionsBottomSheetVisible.observe(this, Observer { isVisible ->
            binding.bottomSheetState = if (isVisible) {
                BottomSheetBehavior.STATE_EXPANDED
            } else {
                BottomSheetBehavior.STATE_COLLAPSED
            }
        })
    }

    fun navController() = findNavController()

}
