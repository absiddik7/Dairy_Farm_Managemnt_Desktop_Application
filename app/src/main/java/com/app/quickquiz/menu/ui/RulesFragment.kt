package com.app.quickquiz.menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.quickquiz.R
import com.app.quickquiz.databinding.FragmentRulesBinding


class RulesFragment : Fragment() {
    private lateinit var binding: FragmentRulesBinding
    private lateinit var rulesViewModel: RulesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rules, container, false)

        rulesViewModel = ViewModelProvider(this).get(RulesViewModel::class.java)
        binding.rulesViewModel = rulesViewModel
        binding.lifecycleOwner = this


        binding.backBtn.setOnClickListener {
            this.findNavController()
                .navigate(RulesFragmentDirections.actionRulesFragmentToMenuFragment())
        }

        binding.rulesToClassicBtn.setOnClickListener {
            this.findNavController()
                .navigate(RulesFragmentDirections.actionRulesFragmentToGamePlayFragment("Random"))
        }


        binding.rulesToArcadeBtn.setOnClickListener {
            this.findNavController()
                .navigate(RulesFragmentDirections.actionRulesFragmentToQuizFilterFragment())
        }

        binding.rulesTpCategoryBtn.setOnClickListener {
            this.findNavController()
                .navigate(RulesFragmentDirections.actionRulesFragmentToCategoryFragment())
        }


        return binding.root
    }
}