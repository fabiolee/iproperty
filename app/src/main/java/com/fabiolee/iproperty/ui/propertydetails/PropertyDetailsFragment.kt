package com.fabiolee.iproperty.ui.propertydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fabiolee.iproperty.databinding.PropertyDetailsFragmentBinding

class PropertyDetailsFragment : Fragment() {

    // The binding property is only valid between onCreateView and onDestroyView.
    private var _binding: PropertyDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val KEY_PROPERTY_ID = "propertyId"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PropertyDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        val propertyId = arguments?.getString(KEY_PROPERTY_ID)
        val viewModel = ViewModelProvider(
            this,
            PropertyDetailsViewModelFactory(propertyId)
        ).get(PropertyDetailsViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            updateLoadingView(loading)
        })
        viewModel.data.observe(viewLifecycleOwner, Observer { data: String ->
            updateContent(data)
        })
    }

    private fun updateContent(data: String) {
        binding.message.text = data
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