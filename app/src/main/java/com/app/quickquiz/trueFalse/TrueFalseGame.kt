package com.app.quickquiz.trueFalse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.quickquiz.R
import com.app.quickquiz.databinding.FragmentTrueFalseGameBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TrueFalseGame : Fragment() {
    private lateinit var binding: FragmentTrueFalseGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_true_false_game, container, false)

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.navView)
        navBar.visibility = View.GONE

        return binding.root

    }

}