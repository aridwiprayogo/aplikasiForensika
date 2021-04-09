package com.project.forensika.history

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.forensika.R
import com.project.forensika.model.History
import com.project.forensika.network.ApiConfig
import com.project.forensika.preferences.SharedPreferencesUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val listHistory = arrayListOf<History.Result>()
    private var activate: Boolean = true

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

        val context = holder.itemView.context

        holder.run {
            bind(history)

            itemView.setOnClickListener {
                activateButtons(holder)
            }

            buttonDetail.setOnClickListener {
                val intent = Intent(context, HistoryDetailActivity::class.java)
                intent.putExtra(ID_HISTORY,history.id)
                context.startActivity(intent)
            }

            val sharedPreferencesUtils = SharedPreferencesUtils(context)
            val token = sharedPreferencesUtils.token
            val header = mapOf(
                    "Authorization" to token
            )

            buttonHapus.setOnClickListener {
                ApiConfig.create().deleteHistory(header, history.id).enqueue(object : Callback<Map<String, String>?> {
                    override fun onResponse(call: Call<Map<String, String>?>, response: Response<Map<String, String>?>) {
                        val map = response.body()
                        val status = map?.get("status")
                        if (response.isSuccessful) {
                            listHistory.removeAt(position)
                            notifyItemRemoved(position)
                            Toast.makeText(context, status, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Map<String, String>?>, t: Throwable) {
                        t.printStackTrace()
                        Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    private fun activateButtons(holder: ViewHolder) {
        if (activate) {
            holder.buttonDetail.visibility = VISIBLE
            holder.buttonHapus.visibility = VISIBLE
            activate = false
        } else {
            holder.buttonDetail.visibility = GONE
            holder.buttonHapus.visibility = GONE
            activate = true
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = listHistory.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewTools: ImageView
        private val textViewAturan: TextView
        private val textViewTool: TextView
        private val textViewDate: TextView
        internal val buttonDetail: Button
        internal val buttonHapus: Button

        init {
            itemView.apply {
                imageViewTools = findViewById(R.id.image_Logo_Tools)
                textViewAturan = findViewById(R.id.tv_aturan)
                textViewTool = findViewById(R.id.tv_tool)
                textViewDate = findViewById(R.id.tv_created_at)
                buttonDetail = findViewById(R.id.btn_detail)
                buttonHapus = findViewById(R.id.btn_hapus)
            }
        }

        fun bind(history: History.Result) {
            val context = itemView.context
            Glide.with(itemView)
                    .load(history.fotoAplikasi)
                    .into(imageViewTools)
            textViewAturan.text = context.getString(R.string.aturan, history.namaAturan)
            textViewTool.text = context.getString(R.string.tool, history.namaAplikasi)
            textViewDate.text = history.createdAt
        }
    }

    private companion object {
        const val ID_APLIKASI: String = "ID APLIKASI"
        const val ID_ATURAN: String = "ID ATURAN"
        const val ID_HISTORY: String = "ID HISTORY"
    }
}