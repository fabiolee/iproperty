package com.fabiolee.iproperty.ui.searchresults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fabiolee.iproperty.R
import com.fabiolee.iproperty.databinding.SearchResultsFragmentBinding
import com.fabiolee.iproperty.repository.model.Item
import com.fabiolee.iproperty.ui.dpToPx
import com.fabiolee.iproperty.ui.propertydetails.PropertyDetailsFragment
import com.fabiolee.iproperty.ui.widget.VerticalSpaceItemDecoration

class SearchResultsFragment : Fragment() {

    // The binding property is only valid between onCreateView and onDestroyView.
    private var _binding: SearchResultsFragmentBinding? = null
    private val binding get() = _binding!!
    private var adapter: SearchResultsAdapter? = null

    companion object {
        const val KEY_SEARCH_TEXT = "searchText"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchResultsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupToolbar()
        setupContent()
    }

    private fun navigateToPropertyDetails(propertyId: String?) {
        findNavController().navigate(
            R.id.property_details_action,
            bundleOf(PropertyDetailsFragment.KEY_PROPERTY_ID to propertyId)
        )
    }

    private fun setupContent() {
        adapter = SearchResultsAdapter(object : Item.Listener {
            override fun onClickContainer(propertyId: String?) {
                navigateToPropertyDetails(propertyId)
            }
        })
        binding.content.addItemDecoration(VerticalSpaceItemDecoration(dpToPx(16)))
        binding.content.adapter = adapter
    }

    private fun setupToolbar() {
        binding.toolbar.setupWithNavController(findNavController())
    }

    private fun setupViewModel() {
        val searchText = arguments?.getString(KEY_SEARCH_TEXT)
        val viewModel = ViewModelProvider(
            this,
            SearchResultsViewModelFactory(searchText)
        ).get(SearchResultsViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            updateLoadingView(loading)
        })
        viewModel.data.observe(viewLifecycleOwner, Observer { data: List<Item>? ->
            updateContent(data)
        })
    }

    private fun updateContent(data: List<Item>?) {
        adapter?.updateData(data)
    }

    private fun updateLoadingView(loading: Boolean) {
        if (loading) {
            binding.toolbar.visibility = View.GONE
            binding.content.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.toolbar.visibility = View.VISIBLE
            binding.content.visibility = View.VISIBLE
            binding.loading.visibility = View.GONE
        }
    }

}