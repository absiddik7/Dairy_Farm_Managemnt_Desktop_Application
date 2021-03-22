package com.app.quickquiz.bookmark

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.quickquiz.R
import com.app.quickquiz.bookmarkDB.BookmarkData
import com.app.quickquiz.bookmarkDB.BookmarkDatabase
import com.app.quickquiz.classic.GamePlayFragmentDirections
import kotlinx.coroutines.*

class BookmarkAdapter(
    private val context: Context,
    private val bookmarkData: List<BookmarkData>,

    ) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewModelHolder>() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


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
        val id = data.id.toString()
        holder.qsText.text = "Q: $qs"
        holder.qsAns.text = "Ans: $ans"
        holder.qsNo.text = "$id."

        fun deleteBookmark(){
            val dataSource = BookmarkDatabase.getInstance(context).bookmarkDatabaseDao
            uiScope.launch {
                withContext(Dispatchers.IO) {
                    val qsExists = dataSource.exists(data.questions)!!
                    if (qsExists) {
                        dataSource.cancelBookmark(data.questions)
                    }
                }
            }
        }

        holder.deletebookmark.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.apply {
                setCancelable(true)
                setTitle("Do you want to Delete?")
                setPositiveButton("Yes") { _, _ ->
                    deleteBookmark()

                }
                setNegativeButton("No", null)
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()








        }

    }

    override fun getItemCount(): Int {
        return bookmarkData.size
    }

    inner class BookmarkViewModelHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var qsNo: TextView = itemView.findViewById(R.id.qsNo_txt)
        var qsText: TextView = itemView.findViewById(R.id.bookmark_qs)
        var qsAns: TextView = itemView.findViewById(R.id.bookmark_qs_ans)
        var deletebookmark: ImageView = itemView.findViewById(R.id.delete_bookmark)
    }




    private fun onQuiting() {

    }

}