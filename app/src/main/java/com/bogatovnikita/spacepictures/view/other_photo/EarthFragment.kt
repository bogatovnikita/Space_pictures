package com.bogatovnikita.spacepictures.view.other_photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.bogatovnikita.spacepictures.R
import com.bogatovnikita.spacepictures.databinding.FragmentEarthBinding
import com.bogatovnikita.spacepictures.viewModel.EarthEpicViewModel
import com.bogatovnikita.spacepictures.viewModel.PictureData

class EarthFragment : Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private val binding: FragmentEarthBinding
        get() = _binding!!


    private val viewModel: EarthEpicViewModel by lazy {
        ViewModelProvider(this).get(EarthEpicViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            //renderData(it)
        })
        viewModel.sendRequest()
    }

//    private fun renderData(pictureData: PictureData) {
//        when (pictureData) {
//            is PictureData.Error -> {}
//            is PictureData.Loading -> {}
//            is PictureData.SuccessEarthEpic -> {
//                with(pictureData) {
//                    if ((serverResponse.image).isEmpty()) {
//                        Toast.makeText(requireActivity(), R.string.no_photo, Toast.LENGTH_LONG)
//                            .show()
//                        binding.imageView.setImageResource(R.drawable.ic_no_photo_vector)
//                    } else {
//                        binding.imageView.load(serverResponse.image) {
//                            placeholder(R.drawable.ic_no_photo_vector)
//                        }
//                    }
//                }
//            }
//        }
//    }
}
