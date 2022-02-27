package com.bogatovnikita.spacepictures.view.other_photo

import ViewPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bogatovnikita.spacepictures.R

class OtherPhotoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val result: View = inflater.inflate(R.layout.fragment_other_photo, container, false)
        val viewPager: ViewPager = result.findViewById(R.id.pager)
        viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        return result
    }

}