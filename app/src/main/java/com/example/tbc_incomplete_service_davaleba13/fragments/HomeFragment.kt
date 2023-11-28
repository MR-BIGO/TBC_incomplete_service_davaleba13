package com.example.tbc_incomplete_service_davaleba13.fragments

import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_incomplete_service_davaleba13.adapters.ChildRvAdapter
import com.example.tbc_incomplete_service_davaleba13.adapters.ParentRvAdapter
import com.example.tbc_incomplete_service_davaleba13.databinding.FragmentHomeBinding
import com.example.tbc_incomplete_service_davaleba13.models.Field
import com.example.tbc_incomplete_service_davaleba13.view_models.HomeFragmentViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var parentRvAdapter: ParentRvAdapter
    private val childRvAdapter by lazy { ChildRvAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
        setData()
        setUpRv()
        listeners()
    }

    private fun listeners(){
        binding.btnRegister.setOnClickListener {
            if(checkFields(childRvAdapter.editTextContent)){
                val action = HomeFragmentDirections.actionHomeFragmentToRegisteredFragment()
                findNavController().navigate(action)
            }
        }
    }

    //Validation doesn't work yet
    private fun checkFields(texts: MutableMap<String, String>) : Boolean{
        for (i in texts){
            if (i.value == "") {
                Toast.makeText(context, "Please, Fill Out The required Fields".plus(" ${i.key}"), Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    private fun setData() {
        //generated by chatgpt. don't know how the line below works yet.
        val fieldType = object : TypeToken<List<List<Field>>>() {}.type
        val fieldList: List<List<Field>> =
            Gson().fromJson(requireContext().assets.readAssetsFile("data.json"), fieldType)

        viewModel.setMyData(fieldList)
    }

    private fun AssetManager.readAssetsFile(filename: String): String =
        open(filename).bufferedReader().use { it.readText() }

    private fun setUpRv() {
        parentRvAdapter = ParentRvAdapter()
        parentRvAdapter.setData(viewModel.data)
        binding.rvFields.layoutManager = LinearLayoutManager(context)
        binding.rvFields.adapter = parentRvAdapter
    }

}