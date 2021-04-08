package com.fabiolee.iproperty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fabiolee.iproperty.R
import com.fabiolee.iproperty.databinding.HomeFragmentBinding
import com.fabiolee.iproperty.ui.searchresults.SearchResultsFragment

class HomeFragment : Fragment() {

    // The binding property is only valid between onCreateView and onDestroyView.
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchView()
    }

    private fun navigateToSearchResults(searchText: String) {
        findNavController().navigate(
            R.id.search_results_action,
            bundleOf(SearchResultsFragment.KEY_SEARCH_TEXT to searchText)
        )
    }

    private fun onEditorAction(actionId: Int, searchText: String): Boolean {
        return if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            navigateToSearchResults(searchText)
            true
        } else {
            false
        }
    }

    private fun setupSearchView() {
        binding.search.setOnEditorActionListener { view, actionId, _ ->
            onEditorAction(actionId, view.text.toString())
        }
    }

}