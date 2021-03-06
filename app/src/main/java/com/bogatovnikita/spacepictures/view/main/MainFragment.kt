package com.bogatovnikita.spacepictures.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.bogatovnikita.spacepictures.R
import com.bogatovnikita.spacepictures.databinding.FragmentMainBinding
import com.bogatovnikita.spacepictures.view.MainActivity
import com.bogatovnikita.spacepictures.view.settings.SettingsFragment
import com.bogatovnikita.spacepictures.viewModel.PictureData
import com.bogatovnikita.spacepictures.viewModel.PODViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private val viewModel: PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

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
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> {
                Toast.makeText(requireContext(), "app_bar_fav", Toast.LENGTH_SHORT).show()
            }
            R.id.app_bar_settings -> {
                requireActivity().supportFragmentManager.beginTransaction().replace(
                    R.id.main_activity_container,
                    SettingsFragment.newInstance()
                ).addToBackStack("")
                    .commit()
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment().show(requireActivity().supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun renderData(pictureData: PictureData) {
        when (pictureData) {
            is PictureData.Error -> {}
            is PictureData.Loading -> {}
            is PictureData.Success -> {
                with(pictureData) {
                    if ((serverResponse.url).isEmpty()) {
                        Toast.makeText(requireActivity(), R.string.no_photo, Toast.LENGTH_LONG)
                            .show()
                        binding.imageView.setImageResource(R.drawable.ic_no_photo_vector)

                    } else {

                        binding.imageView.load(serverResponse.url) {
                            placeholder(R.drawable.ic_no_photo_vector)
                        }
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
        var temp = true
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.included.bottomSheetContainer)
        binding.fab.setOnClickListener {
            if (temp) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                temp = false
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                temp = true
            }
        }
        binding.included.bottomSheetDescriptionHeader.text = ""

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("myLogs", "slideOffset $slideOffset")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}