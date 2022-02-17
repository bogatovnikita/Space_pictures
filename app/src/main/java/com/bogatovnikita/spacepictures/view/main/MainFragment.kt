package com.bogatovnikita.spacepictures.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.bogatovnikita.spacepictures.R
import com.bogatovnikita.spacepictures.databinding.FragmentMainBinding
import com.bogatovnikita.spacepictures.viewModel.PictureData
import com.bogatovnikita.spacepictures.viewModel.PictureViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private val viewModel: PictureViewModel by lazy {
        ViewModelProvider(this).get(PictureViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendRequest()
    }

    private fun renderData(pictureData: PictureData) {
        when (pictureData) {
            is PictureData.Error -> {}
            is PictureData.Loading -> {}
            is PictureData.Success -> {
                binding.imageView.load(pictureData.serverResponse.url) {
                    placeholder(R.drawable.ic_no_photo_vector)
                }
            }
        }

    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}