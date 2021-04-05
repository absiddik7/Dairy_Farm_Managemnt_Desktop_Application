package com.app.quickquiz.score

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.quickquiz.BuildConfig
import com.app.quickquiz.R
import com.app.quickquiz.database.ScoreDatabase
import com.app.quickquiz.databinding.FragmentScoreBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding
    private val args: ScoreFragmentArgs by navArgs()
    private lateinit var scoreViewModel: ScoreViewModel
    private var rightAns: Long = 0L
    private var progress: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)

        val token = args.token
        rightAns = args.rightAns
        val wrongAns = args.wrongAns
        val unAns = args.unAnswered
        val totalQs = rightAns + wrongAns + unAns

        val application = requireNotNull(this.activity).application
        val dataSource = ScoreDatabase.getInstance(application).scoreDatabaseDao
        val viewModelFactory = ScoreViewModelFactory(dataSource,token,rightAns,wrongAns)

        scoreViewModel = ViewModelProvider(this,viewModelFactory).get(ScoreViewModel::class.java)
        binding.scoreViewModel = scoreViewModel
        binding.lifecycleOwner = this

        // Hide Bottom navigation view
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.navView)
        navBar.visibility = View.GONE


        progress = (rightAns.toDouble() / totalQs) * 100
        binding.apply {
            progressBar.progress = progress.toInt()
            progressTxt.text = "${progress.toInt()}%"
        }

        if (progress <= 50) {
            binding.scoreQuotes.text = "Better luck next time!"
        } else if (progress <= 70 && progress > 50) {
            binding.scoreQuotes.text = "Well played!"
        } else {
            binding.scoreQuotes.text = "Excellent!"
        }

        binding.rightAns.text = String.format("%d/%d", rightAns, totalQs)
        binding.unAnswered.text = String.format("%d/%d", unAns, totalQs)
        binding.wrongAns.text = String.format("%d/%d", wrongAns, totalQs)

        scoreViewModel.setDataToDB(true)

        scoreViewModel.navigateToHome.observe(viewLifecycleOwner, { home ->
            home?.let {
                this.findNavController()
                    .navigate(
                        ScoreFragmentDirections.actionScoreFragmentToHomeFragment()
                    )
                scoreViewModel.doneHomeNavigation()
            }
        })


        scoreViewModel.navigateToProfile.observe(viewLifecycleOwner, { profile ->
            profile?.let {
                this.findNavController()
                    .navigate(
                        ScoreFragmentDirections.actionScoreFragmentToProfileFragment()
                    )
                scoreViewModel.doneProfileNavigation()
            }
        })

        scoreViewModel.navigateFromPlayAgain.observe(viewLifecycleOwner, {
            it?.let {
                when (token) {
                    "classic" -> {
                        this.findNavController()
                            .navigate(
                                ScoreFragmentDirections.actionScoreFragmentToGamePlayFragment("Random")
                            )
                        scoreViewModel.doneNavigateFromPlayAgain()
                    }
                    "Arcade" -> {
                        this.findNavController()
                            .navigate(
                                ScoreFragmentDirections.actionScoreFragmentToQuizFilterFragment()
                            )
                        scoreViewModel.doneNavigateFromPlayAgain()
                    }
                    else -> {
                        this.findNavController()
                            .navigate(
                                ScoreFragmentDirections.actionScoreFragmentToCategoryFragment()
                            )
                        scoreViewModel.doneNavigateFromPlayAgain()
                    }
                }
            }
        })

        binding.shareBtn.setOnClickListener {
            try {
                val appLink = "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}".trimIndent()
                val msg = getString(R.string.share_score,rightAns, progress.toInt())
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.putExtra(Intent.EXTRA_TEXT,"$msg $appLink")
                shareIntent.type = "text/plain"
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: ActivityNotFoundException){
                Toast.makeText(requireContext(), "Error occurred", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}