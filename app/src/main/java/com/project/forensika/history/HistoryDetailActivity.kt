package com.project.forensika.history

import android.os.Bundle
import android.util.Base64.*
import android.webkit.WebView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.forensika.R
import com.project.forensika.model.HistoryDetail
import com.project.forensika.network.ApiConfig
import com.project.forensika.preferences.SharedPreferencesUtils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HistoryDetailActivity : AppCompatActivity() {

    private lateinit var textViewMemoryRequirement: TextView
    private lateinit var textViewprocessingSpeed: TextView
    private lateinit var textViewoutputFormat: TextView
    private lateinit var textViewrequiredSkill: TextView
    private lateinit var textViewcost: TextView
    private lateinit var textViewexamFocus: TextView
    private lateinit var textViewFunctionality: TextView

    private lateinit var webView: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_detail)

        webView = findViewById(R.id.webview)

        textViewMemoryRequirement = findViewById(R.id.text_view_memory_requirement)
        textViewprocessingSpeed = findViewById(R.id.text_view_processing_speed)
        textViewoutputFormat = findViewById(R.id.text_view_output_format)
        textViewrequiredSkill = findViewById(R.id.text_view_required_skill)
        textViewcost = findViewById(R.id.text_view_cost)
        textViewexamFocus = findViewById(R.id.text_view_exam_focus)
        textViewFunctionality = findViewById(R.id.text_view_fungsionalitas)

        val sharedPreferencesUtils = SharedPreferencesUtils(this)
        val token = sharedPreferencesUtils.token
        val header = mapOf(
                "Authorization" to token
        )

        val idHistory: Int = intent.getIntExtra(HistoryAdapter.ID_HISTORY, 0)

        ApiConfig.create().getHistoryDetail(header, idHistory).enqueue(object : Callback<HistoryDetail?> {
            override fun onResponse(call: Call<HistoryDetail?>, response: Response<HistoryDetail?>) {
                if (response.isSuccessful) {
                    val historyDetail = response.body()
                    Toast.makeText(this@HistoryDetailActivity, historyDetail?.status, Toast.LENGTH_SHORT).show()
                    val (detail, _, _, _, _, _) = historyDetail?.result!!
                    val (
                            fungsionalitas: List<String>,
                            karakteristik: List<HistoryDetail.Result.Detail.Karakteristik>,
                            _,
                    ) = detail
                    karakteristik.forEachIndexed { index, karakteristikData ->
                        when (index) {
                            0 -> {
                                textViewMemoryRequirement.text = karakteristikData.value
                            }
                            1 -> {
                                textViewprocessingSpeed.text = karakteristikData.value
                            }
                            2 -> {
                                textViewoutputFormat.text = karakteristikData.value
                            }
                            3 -> {
                                textViewrequiredSkill.text = karakteristikData.value
                            }
                            4 -> {
                                textViewcost.text = karakteristikData.value
                            }
                            5 -> {
                                textViewexamFocus.text = karakteristikData.value
                            }
                        }
                    }

                    fungsionalitas.forEach { s: String ->
                        textViewFunctionality.append(s)
                        textViewFunctionality.append("\n\n")
                    }
                }
            }

            override fun onFailure(call: Call<HistoryDetail?>, t: Throwable) {
                t.printStackTrace()
            }
        })


        ApiConfig.create().getHistoryKeterangan(header, idKeterangan = idHistory).enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                val body: ResponseBody? = response.body()
                if (response.isSuccessful) {

                    val encodeHtml = encodeToString(body?.bytes(), NO_PADDING)
                    webView.loadData(encodeHtml, "text/html", "base64")
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
