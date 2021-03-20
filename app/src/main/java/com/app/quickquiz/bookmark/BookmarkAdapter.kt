package com.app.quickquiz.bookmark

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.quickquiz.R
import com.app.quickquiz.bookmarkDB.BookmarkData

class BookmarkAdapter(
    private val context: Context,
    private val bookmarkData: List<BookmarkData>,

    ) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewModelHolder>() {
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BookmarkAdapter.BookmarkViewModelHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_bookmark, null)
        return BookmarkViewModelHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BookmarkAdapter.BookmarkViewModelHolder, position: Int) {
        val data = bookmarkData[position]
        val qs = data.questions
        val ans = data.correctAns
        holder.qsText.text = "Q: $qs"
        holder.qsAns.text = "Ans: $ans"
    }

    override fun getItemCount(): Int {
        return bookmarkData.size
    }

    inner class BookmarkViewModelHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var qsText: TextView = itemView.findViewById(R.id.bookmark_qs)
        var qsAns: TextView = itemView.findViewById(R.id.bookmark_qs_ans)
    }


}