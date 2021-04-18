package com.fabiolee.iproperty.ui.propertydetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fabiolee.iproperty.databinding.PropertyDetailsFragmentBinding
import com.fabiolee.iproperty.repository.model.Item
import com.fabiolee.iproperty.repository.model.Lister
import com.fabiolee.iproperty.repository.model.Phone
import com.fabiolee.iproperty.repository.model.PropertyDetailsResponse
import com.fabiolee.iproperty.ui.dpToPx
import com.fabiolee.iproperty.ui.widget.VerticalSpaceItemDecoration


class PropertyDetailsFragment : Fragment() {

    // The binding property is only valid between onCreateView and onDestroyView.
    private var _binding: PropertyDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private var adapter: PropertyDetailsAdapter? = null

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
        setupToolbar()
        setupContent()
    }

    private fun setupContent() {
        adapter = PropertyDetailsAdapter(object : Item.Listener {
            override fun onClickCall(phones: List<Phone>?) {
                super.onClickCall(phones)
                navigateToContact(phones)
            }

            override fun onClickSms(phones: List<Phone>?) {
                super.onClickSms(phones)
                navigateToSms(phones)
            }
        })
        binding.content.addItemDecoration(VerticalSpaceItemDecoration(dpToPx(16)))
        binding.content.adapter = adapter
    }

    private fun setupToolbar() {
        binding.toolbar.setupWithNavController(findNavController())
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
        viewModel.data.observe(viewLifecycleOwner, Observer { data: PropertyDetailsResponse? ->
            updateContent(data)
            updateFooter(data?.listers)
        })
    }

    private fun updateContent(data: PropertyDetailsResponse?) {
        adapter?.updateData(
            arrayListOf(
                Item(
                    title = data?.title,
                    propertyType = data?.propertyType,
                    prices = data?.prices,
                    cover = data?.cover,
                    publishedAt = data?.publishedAt,
                    address = data?.address,
                    attributes = data?.attributes
                ),
                Item(prices = data?.prices),
                Item(title = data?.title, description = data?.description),
                Item(attributes = data?.attributes),
                Item(featureDescription = data?.featureDescription),
                Item(listers = data?.listers)
            )
        )
    }

    private fun updateFooter(listers: List<Lister>?) {
        val phones = listers?.first()?.contact?.phones
        binding.buttonContact.setOnClickListener { navigateToContact(phones) }
        binding.buttonWhatsapp.setOnClickListener { navigateToWhatsApp(phones) }
    }

    private fun updateLoadingView(loading: Boolean) {
        if (loading) {
            binding.toolbar.visibility = View.GONE
            binding.content.visibility = View.GONE
            binding.footer.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.toolbar.visibility = View.VISIBLE
            binding.content.visibility = View.VISIBLE
            binding.footer.visibility = View.VISIBLE
            binding.loading.visibility = View.GONE
        }
    }

    private fun navigateToContact(phones: List<Phone>?) {
        val number = phones?.find { it.label == Phone.LABEL_MOBILE }?.number
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }

    private fun navigateToSms(phones: List<Phone>?) {
        val number = phones?.find { it.label == Phone.LABEL_MOBILE }?.number
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("sms:$number")
        startActivity(intent)
    }

    private fun navigateToWhatsApp(phones: List<Phone>?) {
        val number = phones?.find { it.label == Phone.LABEL_WHATSAPP }?.number
        val url = "https://api.whatsapp.com/send?phone=$number"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}