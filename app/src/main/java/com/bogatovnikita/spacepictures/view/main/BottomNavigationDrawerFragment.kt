package com.bogatovnikita.spacepictures.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bogatovnikita.spacepictures.R
import com.bogatovnikita.spacepictures.databinding.BottomNavigationLayoutBinding
import com.bogatovnikita.spacepictures.view.other_photo.OtherPhotoFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {
    private var _binding: BottomNavigationLayoutBinding? = null
    private val binding: BottomNavigationLayoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.navigation_other_photo -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace((R.id.main_activity_container), OtherPhotoFragment())
                        .addToBackStack("")
                        .commit()
                }
            }
            true
        }
    }
}