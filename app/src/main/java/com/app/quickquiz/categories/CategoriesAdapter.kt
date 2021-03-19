package com.app.quickquiz.categories

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.quickquiz.R
import com.bumptech.glide.Glide

class CategoriesAdapter(
    private val context: Context,
    private var categoriesData: ArrayList<CategoriesData>
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_categories, null)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val data = categoriesData[position]

        holder.title.text = data.categoryName
        //holder.imageView.setImageResource( R.drawable.img)
//        Glide.with(context)
//            .load(model.categoryImage)
//            .into(holder.imageView)

        holder.itemView.setOnClickListener { view ->
            view.findNavController()
                .navigate(CategoriesFragmentDirections.actionCategoryFragmentToQuickPlayFragment((data.categoryName)))
        }
    }

    override fun getItemCount(): Int {
        return categoriesData.size
    }

    inner class CategoriesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var imageView: ImageView = itemView.findViewById(R.id.categoryImage)
        var title: TextView = itemView.findViewById(R.id.categories_name)

    }
}