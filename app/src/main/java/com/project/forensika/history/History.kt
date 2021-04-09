package com.project.forensika.history

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.forensika.*
import com.project.forensika.checktools.CheckTools
import com.project.forensika.home.Home
import com.project.forensika.model.History
import com.project.forensika.network.ApiConfig
import com.project.forensika.preferences.SharedPreferencesUtils
import com.project.forensika.profile.Profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class History : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var main: CoordinatorLayout
    private lateinit var pullToRefresh: SwipeRefreshLayout
    private lateinit var rvHistory: RecyclerView

    private lateinit var historyAdapter: HistoryAdapter

    private val adapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        if (Build.VERSION.SDK_INT >= 23) {
            window.statusBarColor = resources.getColor(R.color.hijauatas, theme)
        }

        historyAdapter = adapter

        main = findViewById(R.id.main_content)
        rvHistory = findViewById(R.id.rv_list_history)
        rvHistory.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(this@History)
            setHasFixedSize(true)
        }
        bottomNav = findViewById(R.id.menuNav)
        bottomNav.menu.findItem(R.id.history).isChecked = true
        bottomNav.setOnNavigationItemSelectedListener(this)
        pullToRefresh = findViewById(R.id.swipe)
        pullToRefresh.setOnRefreshListener {

        }
        bottomNav = findViewById(R.id.menuNav)
        bottomNav.menu.findItem(R.id.history).isChecked = true
        bottomNav.setOnNavigationItemSelectedListener(this)

        val sharedPreferences = SharedPreferencesUtils(this)
        val token = sharedPreferences.token

        val header = mapOf(
                "Accept" to "application/json",
                "Authorization" to token)
        getDataHistories(header)
    }

    private fun getDataHistories(header: Map<String, String>) {
        ApiConfig.create().getHistories(header).enqueue(object : Callback<History?> {
            override fun onResponse(call: Call<History?>, response: Response<History?>) {
                val history = response.body()
                if (response.isSuccessful) {
                    Toast.makeText(this@History, "response successfully", Toast.LENGTH_SHORT).show()
                }
                history?.result?.let { adapter.submitList(it) }
                Toast.makeText(this@History, "response entah kenapa", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<History?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@History, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.awal -> {
                val intentrwyt = Intent(this@History, Home::class.java)
                startActivity(intentrwyt)
                overridePendingTransition(R.anim.no_slide, R.anim.no_slide)
                finish()
            }
            R.id.history -> {
            }
            R.id.checkbox_ingat_saya -> {
                val intentkandidat = Intent(this@History, CheckTools::class.java)
                startActivity(intentkandidat)
                overridePendingTransition(R.anim.no_slide, R.anim.no_slide)
                finish()
            }
            R.id.profile -> {
                val intentBantuan = Intent(this@History, Profile::class.java)
                startActivity(intentBantuan)
                overridePendingTransition(R.anim.no_slide, R.anim.no_slide)
                finish()
            }
        }
        return true
    }

    override fun onBackPressed() {
        showAlertDialog(message = "Apakah Anda Yakin Ingin Keluar Aplikasi?", positiveAction = {
            this@History.finish()
        })
    }

    private fun showAlertDialog(message: String, positiveAction: (v: View) -> Unit?) {
        val view = LayoutInflater.from(this@History).inflate(
                R.layout.dialog_konfirmasi, null
        )
        val iya = view.findViewById<Button>(R.id.butok)
        val tdk = view.findViewById<Button>(R.id.buttdk)
        val pesan = view.findViewById<TextView>(R.id.pesan)
        val builder = AlertDialog.Builder(this@History, R.style.AlertDialog)
        builder.setView(view)
        val alertDialog = builder.create()
        pesan.text = message
        iya.text = "IYA"
        tdk.text = "TIDAK"
        iya.setOnClickListener {
            positiveAction.invoke(it)
            alertDialog.cancel()
        }
        tdk.setOnClickListener { alertDialog.cancel() }
        if (alertDialog.window != null) {
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(0))
        }
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

}