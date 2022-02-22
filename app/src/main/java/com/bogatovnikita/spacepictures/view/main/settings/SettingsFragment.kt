package com.bogatovnikita.spacepictures.view.main.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bogatovnikita.spacepictures.R
import com.bogatovnikita.spacepictures.databinding.FragmentSettingsBinding
import com.bogatovnikita.spacepictures.view.MainActivity
import com.bogatovnikita.spacepictures.view.ThemeDefault
import com.bogatovnikita.spacepictures.view.ThemeFirst
import com.bogatovnikita.spacepictures.view.ThemeSecond
import com.google.android.material.chip.Chip

class SettingsFragment : Fragment(), View.OnClickListener {

    private lateinit var parentActivity: MainActivity
    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = requireActivity() as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            binding.chipGroup.findViewById<Chip>(checkedId)?.let { it ->
                Toast.makeText(requireContext(), "${it.text} $checkedId", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.chipEntry.setOnCloseIconClickListener {
            Toast.makeText(requireContext(), "chipEntry close", Toast.LENGTH_SHORT).show()
        }
        binding.defaultThemeRb.setOnClickListener(this)
        binding.firstThemeRb.setOnClickListener(this)
        binding.secondThemeRb.setOnClickListener(this)

        when (parentActivity.getCurrentTheme()) {
            0 -> binding.radioGroup.check(R.id.default_theme_rb)
            1 -> binding.radioGroup.check(R.id.first_theme_rb)
            2 -> binding.radioGroup.check(R.id.second_theme_rb)
            -1 -> binding.radioGroup.check(R.id.default_theme_rb)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.default_theme_rb -> {
                parentActivity.setCurrentTheme(ThemeDefault)
                parentActivity.recreate()
            }
            R.id.first_theme_rb -> {
                parentActivity.setCurrentTheme(ThemeFirst)
                parentActivity.recreate()
            }
            R.id.second_theme_rb -> {
                parentActivity.setCurrentTheme(ThemeSecond)
                parentActivity.recreate()
            }
        }
    }
}