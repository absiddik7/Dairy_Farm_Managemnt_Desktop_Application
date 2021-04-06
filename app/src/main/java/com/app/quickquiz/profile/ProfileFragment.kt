package com.app.quickquiz.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.app.quickquiz.R
import com.app.quickquiz.database.ScoreDatabase
import com.app.quickquiz.databinding.FragmentProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.navView)
        navBar.visibility = View.VISIBLE


        val application = requireNotNull(this.activity).application
        val dataSource = ScoreDatabase.getInstance(application).scoreDatabaseDao
        val viewModelFactory = ProfileViewModelFactory(dataSource)

        profileViewModel =
            ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
        binding.profileViewModel = profileViewModel
        binding.lifecycleOwner = this

        profileViewModel.apply {
            getAllScoreSum()
            getClassicScore()
            getArcadeScore()
        }

        profileViewModel.apply{

            allCorrectScoreSum .observe(viewLifecycleOwner, {
                binding.totalRightAns.text = it.toString()
            })

            allWrongScoreSum .observe(viewLifecycleOwner, {
                binding.totalWrongAns.text = it.toString()
            })

            totalAnsweredQS .observe(viewLifecycleOwner, {
                binding.totalQs.text = it.toString()
            })

            averageAccuracy .observe(viewLifecycleOwner, {
                binding.apply {
                    profileProgressBar.progress = it.toInt()
                    percentageTxt.text = "${it.toInt()}%"
                }
            })

        }


        profileViewModel.apply {

            classicCorrectScore.observe(viewLifecycleOwner, {
                binding.classicRightScore.text = it.toString()
            })

            classicWrongScore.observe(viewLifecycleOwner, {
                binding.classicWrongScore.text = it.toString()
            })

            classicHighScore.observe(viewLifecycleOwner, {
                binding.classicHighScore.text = it.toString()
            })

            classicAccuracy.observe(viewLifecycleOwner, {
                binding.classicAccuracy.text = "${it.toInt()}%"
            })
        }

        profileViewModel.apply {

            arcadeCorrectScore.observe(viewLifecycleOwner, {
                binding.arcadeRightScore.text = it.toString()
            })

            arcadeWrongScore.observe(viewLifecycleOwner, {
                binding.arcadeWrongScore.text = it.toString()
            })

            arcadeHighScore.observe(viewLifecycleOwner, {
                binding.arcadeHighScore.text = it.toString()
            })

            arcadeAccuracy.observe(viewLifecycleOwner, {
                binding.arcadeAccuracy.text = "${it.toInt()}%"
            })
        }

        profileViewModel.getAllScores()

        profileViewModel.allScores.observe(viewLifecycleOwner, {
            val adapter = ProfileAdapter(requireContext(), it)
            adapter.notifyDataSetChanged()

            binding.profileRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            binding.profileRecyclerView.adapter = adapter
        })


        return binding.root
    }

}