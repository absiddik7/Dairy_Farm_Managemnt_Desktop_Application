package com.app.quickquiz.menu

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.quickquiz.BuildConfig
import com.app.quickquiz.R
import com.app.quickquiz.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var menuViewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        menuViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        binding.menuViewModel = menuViewModel
        binding.lifecycleOwner = this

        menuViewModel.navigateToRulesFragment.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController()
                    .navigate(MenuFragmentDirections.actionMenuFragmentToRulesFragment())
                menuViewModel.doneRulesFragmentNavigation()
            }
        })

        menuViewModel.navigateToAboutUsFragment.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController()
                    .navigate(MenuFragmentDirections.actionMenuFragmentToAboutFragment())
                menuViewModel.doneAboutUsFragmentNavigation()
            }
        })

        binding.shareApp.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                var shareMessage = "\nPlay Quick Quiz general knowledge app! Download For free: "
                shareMessage =
                    "$shareMessage https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}".trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error occurred", Toast.LENGTH_SHORT).show()
            }
        }

        binding.rateUs.setOnClickListener {
            val packageName = "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}".trimIndent()
            try {
                val uri: Uri = Uri.parse("market://details?id=$packageName")
                startActivity(Intent(Intent.ACTION_VIEW,uri))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=$packageName")))
            }
        }

        return binding.root
    }

}