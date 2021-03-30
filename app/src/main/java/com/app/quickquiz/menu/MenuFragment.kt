package com.app.quickquiz.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.quickquiz.R
import com.app.quickquiz.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var menuViewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        menuViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        binding.menuViewModel = menuViewModel
        binding.lifecycleOwner = this

        menuViewModel.navigateToRulesFragment.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToRulesFragment())
                menuViewModel.doneRulesFragmentNavigation()
            }
        })




        return binding.root
    }

}