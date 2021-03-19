package com.app.quickquiz.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.quickquiz.R
import com.app.quickquiz.databinding.FragmentCategoriesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var database: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)

        categoriesViewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        binding.categoriesViewModel = categoriesViewModel
        binding.lifecycleOwner = this

        // Hide Bottom navigation view
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.navView)
        navBar.visibility = View.GONE

        categoriesViewModel.navigateToHome.observe(viewLifecycleOwner, { home ->
            home?.let {
                this.findNavController()
                    .navigate(
                        CategoriesFragmentDirections.actionCategoryFragmentToHomeFragment()
                    )
                categoriesViewModel.doneHomeNavigation()
            }
        })

        val categories = ArrayList<CategoriesData>()

        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val scienceQS = obj.getJSONArray("categories")

            for (i in 0 until scienceQS.length()) {
                val qsObj = scienceQS.getJSONObject(i)
                val id = qsObj.getString("id")
                val name = qsObj.getString("name")
                val img = qsObj.getString("img")

                val qsDetails = CategoriesData(id, name, img)
                categories.add(qsDetails)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        binding.categoriesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = CategoriesAdapter(requireContext(), categories)
        binding.categoriesRecyclerView.adapter = adapter

        return binding.root
    }

    private fun getJSONFromAssets(): String? {
        val json: String?
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = requireContext().assets.open("Categories.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}