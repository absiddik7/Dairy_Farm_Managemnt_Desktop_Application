package com.app.quickquiz.bookmark

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.quickquiz.R
import com.app.quickquiz.bookmarkDB.BookmarkDatabase
import com.app.quickquiz.databinding.FragmentBookmarkBinding
import com.app.quickquiz.profile.ProfileViewModel


class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var bookmarkViewModel:BookmarkViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_bookmark, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = BookmarkDatabase.getInstance(application).bookmarkDatabaseDao
        val viewModelFactory = BookmarkViewModelFactory(dataSource)

        bookmarkViewModel =
            ViewModelProvider(this, viewModelFactory).get(BookmarkViewModel::class.java)
        binding.lifecycleOwner = this

        bookmarkViewModel.getAllBookmarked()

        bookmarkViewModel.allBookmark.observe(viewLifecycleOwner,{

            val bookmarkDataSize = it.size
            if (bookmarkDataSize < 1){
                binding.emptyBookmarkTxt.text = "Empty Bookmark!"
            }
            val adapter = BookmarkAdapter(requireContext(),it)
            adapter.notifyDataSetChanged()

            binding.recyclerViewBookmark.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewBookmark.adapter = adapter
        })



        return binding.root
    }

}