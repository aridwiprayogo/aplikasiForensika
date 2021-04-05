package com.project.forensika.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.forensika.R
import com.project.forensika.model.RecommendationTools

class RecommendationToolsAdapter : RecyclerView.Adapter<RecommendationToolsAdapter.ViewHolder>() {

    private var listRecommendationTools = arrayListOf<RecommendationTools.Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_item_recommendation_tools, parent, false)
        return ViewHolder(view = view)
    }

    fun addItem(list : List<RecommendationTools.Result>){
        listRecommendationTools.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = listRecommendationTools[position]
        holder.run {
            Glide.with(holder.itemView.context)
                    .load(result.foto)
                    .into(imageLogo)
            textJudul.text = result.aplikasi
            textAturan.text = result.aturan
            textOrder.text = (position+1).toString()
        }
    }

    override fun getItemCount(): Int = listRecommendationTools.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val imageLogo : ImageView
        internal val textJudul : TextView
        internal val textAturan: TextView
        internal val textOrder : TextView

        init {
            with(receiver = view){
                imageLogo  = findViewById(R.id.imageView)
                textJudul  = findViewById(R.id.textView_judul)
                textAturan = findViewById(R.id.text_aturan)
                textOrder  = findViewById(R.id.textView_order)
            }
        }
    }

}