package com.app.quickquiz.profile

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.quickquiz.R
import com.app.quickquiz.database.CategoriesScore
import com.bumptech.glide.Glide

class ProfileAdapter(
    private val context: Context,
    private val categoriesData: List<CategoriesScore>,
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewModelHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ProfileAdapter.ProfileViewModelHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_profile, null)
        return ProfileViewModelHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProfileAdapter.ProfileViewModelHolder, position: Int) {
        val data = categoriesData[position]
        val playedQSNo = data.correctAns + data.wrongAns
        val accuracy = (data.correctAns.toDouble() / playedQSNo) * 100

        holder.catName.text = data.categoryName
        holder.totalQs.text = "Questions: $playedQSNo"
        holder.accuracy.text = "${accuracy.toInt()}%"
        holder.imgView.setImageResource(data.categoryImage)

        Glide.with(context)
            .load(data.categoryImage)
            .into(holder.imgView)
    }

    override fun getItemCount(): Int {
        return categoriesData.size
    }

    inner class ProfileViewModelHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgView: ImageView = itemView.findViewById(R.id.cat_img)
        var catName: TextView = itemView.findViewById(R.id.cat_name)
        var totalQs: TextView = itemView.findViewById(R.id.played_qs_no)
        var accuracy: TextView = itemView.findViewById(R.id.average_accuracy)
        var layout: ConstraintLayout = itemView.findViewById(R.id.profile_item_layout)
    }
}