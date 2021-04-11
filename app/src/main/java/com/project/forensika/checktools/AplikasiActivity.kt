package com.project.forensika.checktools

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.project.forensika.R
import com.project.forensika.model.CheckTools.Result
import java.util.ArrayList

class AplikasiActivity : AppCompatActivity() {
    private lateinit var imageViewTools: ImageView
    private lateinit var textViewAturan: TextView
    private lateinit var textViewTool: TextView
    private lateinit var textViewDate: TextView
    private lateinit var buttonOk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aplikasi)

        val aplikasiList: ArrayList<Result>? = intent.getParcelableArrayListExtra(CheckTools.CHECK_TOOLS_RESULT)

        imageViewTools = findViewById(R.id.image_Logo_Tools)
        textViewAturan = findViewById(R.id.tv_aturan)
        textViewTool = findViewById(R.id.tv_tool)
        textViewDate = findViewById(R.id.tv_created_at)
        buttonOk = findViewById(R.id.btn_ok)

        aplikasiList?.forEach { aplikasi ->
            textViewAturan.text = getString(R.string.aturan, aplikasi.namaAturan ?: "")
            textViewTool.text = getString(R.string.tool, aplikasi.namaAplikasi ?: "")
        }
//        Glide.with(imageViewTools)
//                .load(aplikasi.fotoAplikasi)
//                .into(imageViewTools)

//        textViewDate.text = aplikasi.createdAt

        buttonOk.setOnClickListener {
            finish()
        }
    }
}