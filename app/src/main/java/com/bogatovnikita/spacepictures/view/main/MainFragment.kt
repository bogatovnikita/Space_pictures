package com.bogatovnikita.spacepictures.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.bogatovnikita.spacepictures.R
import com.bogatovnikita.spacepictures.databinding.FragmentMainBinding
import com.bogatovnikita.spacepictures.viewModel.PictureData
import com.bogatovnikita.spacepictures.viewModel.PictureViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private val viewModel: PictureViewModel by lazy {
        ViewModelProvider(this).get(PictureViewModel::class.java)
    }

    lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

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
        bottomSheet()
    }

    private fun renderData(pictureData: PictureData) {
        when (pictureData) {
            is PictureData.Error -> {}
            is PictureData.Loading -> {}
            is PictureData.Success -> {
                with(pictureData) {
                    binding.imageView.load(serverResponse.url) {
                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                    binding.included.bottomSheetDescriptionHeader.text = title
                    binding.included.bottomSheetDescription.text = explanation
                }
            }
        }

    }

    companion object {
        fun newInstance() = MainFragment()
    }

    private fun bottomSheet() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.included.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        binding.included.bottomSheetDescriptionHeader.text = ""

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("mylogs", "slideOffset $slideOffset")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}