package com.app.quickquiz.menu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

        binding = DataBindingUtil.inflate( inflater,R.layout.fragment_rules, container, false)

        rulesViewModel = ViewModelProvider(this).get(RulesViewModel::class.java)
        binding.rulesViewModel = rulesViewModel
        binding.lifecycleOwner = this

        rulesViewModel.navigateToMenuFragment.observe(viewLifecycleOwner,{
            it?.let {
                this.findNavController().navigate(RulesFragmentDirections.actionRulesFragmentToMenuFragment())
                rulesViewModel.doneMenuFragmentNavigation()
            }
        })

        rulesViewModel.navigateToClassic.observe(viewLifecycleOwner,{
            it?.let {
                this.findNavController().navigate(RulesFragmentDirections.actionRulesFragmentToGamePlayFragment("Random"))
                rulesViewModel.doneClassicNavigation()
            }
        })

        rulesViewModel.navigateToArcade.observe(viewLifecycleOwner,{
            it?.let {
                this.findNavController().navigate(RulesFragmentDirections.actionRulesFragmentToQuizFilterFragment())
                rulesViewModel.doneArcadeNavigation()
            }
        })

        rulesViewModel.navigateToCategory.observe(viewLifecycleOwner,{
            it?.let {
                this.findNavController().navigate(RulesFragmentDirections.actionRulesFragmentToCategoryFragment())
                rulesViewModel.doneCategoryNavigation()
            }
        })





        return binding.root
    }
}