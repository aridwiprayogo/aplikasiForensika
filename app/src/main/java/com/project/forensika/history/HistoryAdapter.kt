package com.project.forensika.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.forensika.R
import com.project.forensika.model.History

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val listHistory = arrayListOf<History.Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_item_history, parent, false)
        return ViewHolder(
                itemView = view
        )
    }

    fun submitList(result: List<History.Result>) {
        result.let { listHistory.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = listHistory[position]

        holder.run{
            bind(history)
        }

        holder.itemView.setOnClickListener {
//            val intent = Intent(context, HistoryDetail::class.java)
//            intent.putExtra(ID_APLIKASI, history.idAplikasi)
//            intent.putExtra(ID_ATURAN, history.idAturan)
//            context.startActivity(intent)
        }
    }

    override fun getItemCount()= listHistory.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewTools: ImageView
        private val textViewAturan: TextView
        private val textViewTool: TextView
        private val textViewDate: TextView

        init {
            itemView.apply {
                imageViewTools = findViewById(R.id.image_Logo_Tools)
                textViewAturan = findViewById(R.id.tv_aturan)
                textViewTool   = findViewById(R.id.tv_tool)
                textViewDate   = findViewById(R.id.tv_created_at)
            }
        }

        fun bind(history: History.Result) {
            val context = itemView.context
//            Glide.with(itemView)
//                    .load(history)
//                    .into(imageViewTools)
            textViewAturan.text = context.getString(R.string.aturan, history.namaAturan)
            textViewTool.text = context.getString(R.string.tool, history.namaAplikasi)
            textViewDate.text = history.id.toString()
        }
    }

    private companion object {
        const val ID_APLIKASI: String = "ID APLIKASI"
        const val ID_ATURAN: String = "ID ATURAN"
    }
}