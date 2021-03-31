package com.app.quickquiz.menu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.app.quickquiz.R
import com.app.quickquiz.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding:FragmentAboutBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_about, container, false)


        binding.aboutBackBtn.setOnClickListener{
            this.findNavController().navigate(AboutFragmentDirections.actionAboutFragmentToMenuFragment())
        }


        return binding.root
    }

}