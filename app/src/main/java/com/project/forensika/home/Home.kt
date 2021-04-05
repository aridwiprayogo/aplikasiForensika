package com.project.forensika.home

import android.content.Intent
import android.graphics.drawable.ColorDrawable
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
import com.project.forensika.Check
import com.project.forensika.Profile
import com.project.forensika.R
import com.project.forensika.history.History
import com.project.forensika.model.RecommendationTools
import com.project.forensika.network.ApiConfig
import com.project.forensika.preferences.SharedPreferencesUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Home : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var main: CoordinatorLayout
    private lateinit var pullToRefresh: SwipeRefreshLayout
    private lateinit var recyclerViewListTools: RecyclerView

    private lateinit var bottomNav: BottomNavigationView

    private val toolsAdapter by lazy {
        RecommendationToolsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        main = findViewById(R.id.main_content)
        bottomNav = findViewById(R.id.menuNav)
        pullToRefresh = findViewById(R.id.swipe)
        recyclerViewListTools = findViewById(R.id.rv_list_recommendation_tools)

        val adapter = toolsAdapter

        recyclerViewListTools.apply {
            this.adapter = this@Home.toolsAdapter
            layoutManager = LinearLayoutManager(this@Home)
            setHasFixedSize(true)
        }

        val menuItem = bottomNav.menu.findItem(R.id.awal)
        menuItem.isChecked = true
        bottomNav.setOnNavigationItemSelectedListener(this)

        val sharedPreferences = SharedPreferencesUtils(this)
        val token = sharedPreferences.token
        val apiService = ApiConfig.create()

        val header = mapOf(
                "Accept" to "application/json",
                "Authorization" to token)

        apiService.getRecommendationsTools(header).enqueue(object : Callback<RecommendationTools> {
            override fun onResponse(call: Call<RecommendationTools>, response: Response<RecommendationTools>) {
                val recommendationTools = response.body()
                if (response.isSuccessful) {
                    adapter.addItem(recommendationTools?.result!!)
                    Toast.makeText(this@Home, "success", Toast.LENGTH_SHORT).show()
                }

                Toast.makeText(this@Home, response.message(), Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<RecommendationTools>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@Home, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this@Home, R.style.AlertDialog)
        val alertDialog = builder.create()
        val view = LayoutInflater.from(this@Home).inflate(R.layout.dialog_konfirmasi, null)

        builder.setView(view)

        val iya = view.findViewById<Button>(R.id.butok)
        val tdk = view.findViewById<Button>(R.id.buttdk)
        val pesan = view.findViewById<TextView>(R.id.pesan)

        pesan.text = getString(R.string.message_exit)
        "IYA".also { iya.text = it }
        "TIDAK".also { tdk.text = it }

        iya.setOnClickListener {
            alertDialog.cancel()
            finish()
        }

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(0))
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.awal -> {
            }
            R.id.history -> {
                val intentrwyt = Intent(this@Home, History::class.java)
                startActivity(intentrwyt)
                overridePendingTransition(R.anim.no_slide, R.anim.no_slide)
                finish()
            }
            R.id.checkbox_ingat_saya -> {
                val intentkandidat = Intent(this@Home, Check::class.java)
                startActivity(intentkandidat)
                overridePendingTransition(R.anim.no_slide, R.anim.no_slide)
                finish()
            }
            R.id.profile -> {
                val intentBantuan = Intent(this@Home, Profile::class.java)
                startActivity(intentBantuan)
                overridePendingTransition(R.anim.no_slide, R.anim.no_slide)
                finish()
            }
        }
        return true
    }
}
