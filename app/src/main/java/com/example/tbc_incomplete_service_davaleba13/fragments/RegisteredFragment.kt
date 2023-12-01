package com.example.tbc_incomplete_service_davaleba13.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tbc_incomplete_service_davaleba13.BaseFragment
import com.example.tbc_incomplete_service_davaleba13.databinding.FragmentRegisteredBinding
import com.example.tbc_incomplete_service_davaleba13.view_models.RegisteredFragmentViewModel

class RegisteredFragment :
    BaseFragment<FragmentRegisteredBinding>(FragmentRegisteredBinding::inflate) {

    private val args: RegisteredFragmentArgs by navArgs()
    private val viewModel: RegisteredFragmentViewModel by viewModels()

    override fun setUp() {
        viewModel.saveValues(args.fieldTexts.map)
        binding.tvValues.text = viewModel.fieldTexts.toString()
    }
}