package com.app.quickquiz.home

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.app.quickquiz.R
import com.app.quickquiz.categories.CategoriesData
import com.app.quickquiz.categories.CategoriesModel
import com.app.quickquiz.database.ScoreDatabase
import com.app.quickquiz.databinding.FragmentHomeBinding
import com.app.quickquiz.score.ScoreViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.navView)
        navBar.visibility = View.VISIBLE

        val application = requireNotNull(this.activity).application
        val dataSource = ScoreDatabase.getInstance(application).scoreDatabaseDao
        val viewModelFactory = HomeViewModelFactory(dataSource)

        homeViewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = this


        // Navigate to QuickPlayFragment
        homeViewModel.navigateToQuickQuiz.observe(viewLifecycleOwner, { quickPlay ->
            quickPlay?.let {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToGamePlayFragment("Random")
                )
                homeViewModel.doneQuickPlayNavigation()
            }
        })

        // Navigate to Time Trial Fragment
        homeViewModel.navigateToQuizFilter.observe(viewLifecycleOwner, { timeTrial ->
            timeTrial?.let {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToQuizFilterFragment()
                )
                homeViewModel.doneQuizFilterNavigation()
            }
        })

        // Navigate to Category fragment
        homeViewModel.navigateToCategory.observe(viewLifecycleOwner, { category ->
            category?.let {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToCategoryFragment()
                )
                homeViewModel.doneCategoryNavigation()
            }
        })

        homeViewModel.setInitialScore()

        return binding.root
    }
}