package com.app.quickquiz.menu.ui

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.quickquiz.BuildConfig
import com.app.quickquiz.R
import com.app.quickquiz.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {
    private lateinit var binding:FragmentAboutBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)


        binding.aboutBackBtn.setOnClickListener {
            this.findNavController()
                .navigate(AboutFragmentDirections.actionAboutFragmentToMenuFragment())
        }

        binding.appVersionTxt.text = "Version ${BuildConfig.VERSION_NAME}"


        binding.feedbackBtn.setOnClickListener {
            try {
                val emailIntent = Intent(Intent.ACTION_SEND)
                val recipients = arrayOf("quickquiz@gmail.com")
                emailIntent.apply {
                    putExtra(Intent.EXTRA_EMAIL, recipients)
                    putExtra(Intent.EXTRA_SUBJECT, "")
                    putExtra(Intent.EXTRA_TEXT, "")
                    putExtra(Intent.EXTRA_CC, "mailcc@gmail.com")
                    type = "text/html"
                    setPackage("com.google.android.gm")
                }
                startActivity(Intent.createChooser(emailIntent, "Send mail"))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(requireContext(), "Error occurred", Toast.LENGTH_SHORT).show()
            }

        }


        return binding.root
    }

}