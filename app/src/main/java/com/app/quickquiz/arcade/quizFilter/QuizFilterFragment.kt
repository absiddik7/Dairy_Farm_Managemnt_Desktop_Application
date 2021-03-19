package com.app.quickquiz.arcade.quizFilter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.quickquiz.R
import com.app.quickquiz.categories.CategoriesModel
import com.app.quickquiz.databinding.FragmentQuizFilterBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore

class QuizFilterFragment : Fragment() {

    private lateinit var binding: FragmentQuizFilterBinding
    private lateinit var quizFilterViewModel: QuizFilterViewModel
    private lateinit var categoryName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_filter, container, false)

        // hide bottom navigation view
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.navView)
        navBar.visibility = View.GONE

        val category = ArrayList<String>()

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            category
        )

        val cateNameFile =
            listOf("Random","Ordinary",
                "Geography",
                "History",
                "Science",
                "Sports",
                "Universe")

        for (fileName in cateNameFile) {
            category.add(fileName)
            adapter.notifyDataSetChanged()
        }
        //category.add(0, "Random")
        binding.cSpinner.adapter = adapter
        binding.cSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                categoryName = adapterView?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        quizFilterViewModel = ViewModelProvider(this).get(QuizFilterViewModel::class.java)
        binding.quizFilterViewModel = quizFilterViewModel
        binding.lifecycleOwner = this

        quizFilterViewModel.navigateQuizFilterToHome.observe(viewLifecycleOwner, { home ->
            home?.let {
                this.findNavController()
                    .navigate(QuizFilterFragmentDirections.actionQuizFilterFragmentToHomeFragment())
                quizFilterViewModel.doneHomeNavigation()
            }
        })

        quizFilterViewModel.navigateToTimeTrial.observe(viewLifecycleOwner, { arcade ->
            arcade?.let {
                this.findNavController()
                    .navigate(
                        QuizFilterFragmentDirections.actionQuizFilterFragmentToTimeTrialFragment(
                            categoryName, 1
                        )
                    )
                quizFilterViewModel.doneTimeTrialNavigation()
            }
        })

        return binding.root
    }
}