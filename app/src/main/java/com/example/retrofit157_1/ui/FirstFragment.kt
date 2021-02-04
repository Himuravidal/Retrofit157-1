package com.example.retrofit157_1.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofit157_1.R
import com.example.retrofit157_1.databinding.FragmentFirstBinding
import com.example.retrofit157_1.viewModel.MarsViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding : FragmentFirstBinding
    private val viewModel : MarsViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MarsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        // Observador vieja confiable
        viewModel.marsTerrainLiveDataFromDB.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("LISTADO", it.toString())
                adapter.update(it)
            }
        })

        adapter.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                val bundle = Bundle()
                bundle.putString("id", it.id)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        })
    }
}