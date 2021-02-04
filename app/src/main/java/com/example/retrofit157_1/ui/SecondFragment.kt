package com.example.retrofit157_1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.retrofit157_1.R
import com.example.retrofit157_1.databinding.FragmentSecondBinding
import com.example.retrofit157_1.viewModel.MarsViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel : MarsViewModel by activityViewModels()
    var idMars: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idMars = it.getString("id", "")
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTerrainByID(idMars).observe(viewLifecycleOwner, Observer {
            it?.let {
                Glide.with(binding.ivTerrain)
                    .load(it.srcImg).centerCrop().into(binding.ivTerrain)
                binding.tvPrice.text = it.price.toString()
                binding.tvType.text = it.type
            }
        })
    }
}