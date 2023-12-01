package com.example.tbc_incomplete_service_davaleba13.fragments

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_incomplete_service_davaleba13.BaseFragment
import com.example.tbc_incomplete_service_davaleba13.adapters.ParentRvAdapter
import com.example.tbc_incomplete_service_davaleba13.databinding.FragmentHomeBinding
import com.example.tbc_incomplete_service_davaleba13.models.MapIntString
import com.example.tbc_incomplete_service_davaleba13.view_models.HomeFragmentViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var parentRvAdapter: ParentRvAdapter
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun setUp() {
        setUpRv()
        listeners()
    }

    private fun listeners() {
        binding.btnRegister.setOnClickListener {
            if (validate()) {
                val action = HomeFragmentDirections.actionHomeFragmentToRegisteredFragment(
                    MapIntString(allFieldData())
                )
                findNavController().navigate(action)
            } else {
                Toast.makeText(
                    context,
                    "Please, fill out the field '${viewModel.emptyRequiredField}'",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setUpRv() {
        parentRvAdapter = ParentRvAdapter()
        parentRvAdapter.setData(viewModel.data)
        binding.rvFields.layoutManager = LinearLayoutManager(context)
        binding.rvFields.adapter = parentRvAdapter
    }

    private fun validate(): Boolean = viewModel.requiredValidation(requiredFieldData())

    private fun allFieldData(): Map<Int, String> = parentRvAdapter.getFields()

    private fun requiredFieldData(): Map<String, String> = parentRvAdapter.getRequiredFields()
}