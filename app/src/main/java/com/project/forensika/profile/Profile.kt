package com.project.forensika.profile

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.forensika.Check
import com.project.forensika.MainActivity
import com.project.forensika.R
import com.project.forensika.SharedPrefUtils
import com.project.forensika.history.History
import com.project.forensika.home.Home
import com.project.forensika.model.UserProfile
import com.project.forensika.network.ApiConfig
import com.project.forensika.preferences.SharedPreferencesUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var butedit: Button
    private lateinit var butubhpas: Button
    private lateinit var keluar: RelativeLayout
    private lateinit var textNama: TextView
    private lateinit var textEmail: TextView
    private lateinit var textRole: TextView
    private lateinit var linprofil: LinearLayout
    private lateinit var linpass: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        if (Build.VERSION.SDK_INT >= 23) {
            window.statusBarColor = resources.getColor(R.color.hijauatas, theme)
        }

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        bottomNav = findViewById(R.id.menuNav)
        bottomNav.menu.findItem(R.id.profile).isChecked = true
        bottomNav.setOnNavigationItemSelectedListener(this)
        butedit = findViewById(R.id.butedit)
        butubhpas = findViewById(R.id.butgntpas)

        textNama = findViewById(R.id.txnama)

        textEmail = findViewById(R.id.txemail)

        textRole = findViewById(R.id.txrole)

        keluar = findViewById(R.id.keluar)

        butedit.setOnClickListener {
            val intent = Intent(this@Profile, EditProfileActivity::class.java)
            startActivity(intent)
        }

        butubhpas.setOnClickListener {
            val intent = Intent(this@Profile, EditPasswordProfileActivity::class.java)
            startActivity(intent)
        }

        val sharedPreferencesUtils = SharedPreferencesUtils(this)
        val token = sharedPreferencesUtils.token
        val header = mapOf(
                "Authorization" to token
        )

        ApiConfig.create().getProfile(header).enqueue(object : Callback<UserProfile?> {
            override fun onResponse(call: Call<UserProfile?>, response: Response<UserProfile?>) {
                if (response.isSuccessful) {
                    val userProfile: UserProfile? = response.body()
                    val (_, _, email: String, _, _, name: String, role, _: String?) = userProfile?.result!!
                    textNama.text = name
                    textEmail.text = email
                    textRole.text = role
                }
            }

            override fun onFailure(call: Call<UserProfile?>, t: Throwable) {
                t.printStackTrace()

            }
        })

        keluar.setOnClickListener {
            val builder = AlertDialog.Builder(this@Profile, R.style.AlertDialog)
            val view = LayoutInflater.from(this@Profile).inflate(
                    R.layout.dialog_konfirmasi, null
            )
            builder.setView(view)
            val alertDialog = builder.create()
            val iya = view.findViewById<Button>(R.id.butok)
            val tdk = view.findViewById<Button>(R.id.buttdk)
            val pesan = view.findViewById<TextView>(R.id.pesan)
            pesan.text = "Apakah Anda Yakin Ingin Keluar Dari Akun Anda?"
            iya.text = "IYA"
            tdk.text = "TIDAK"
            iya.setOnClickListener {
                alertDialog.cancel()
                SharedPrefUtils.logOut(this@Profile)
                val intentrwyt = Intent(this@Profile, MainActivity::class.java)
                intentrwyt.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intentrwyt)
                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
                finish()
            }
            tdk.setOnClickListener { alertDialog.cancel() }
            if (alertDialog.window != null) {
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            alertDialog.setCancelable(true)
            alertDialog.show()
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.awal -> {
                val intentrwyt = Intent(this@Profile, Home::class.java)
                startActivity(intentrwyt)
                overridePendingTransition(R.anim.no_slide, R.anim.no_slide)
                finish()
            }
            R.id.history -> {
                val intentkandidat = Intent(this@Profile, History::class.java)
                startActivity(intentkandidat)
                overridePendingTransition(R.anim.no_slide, R.anim.no_slide)
                finish()
            }
            R.id.checkbox_ingat_saya -> {
                val intentBantuan = Intent(this@Profile, Check::class.java)
                startActivity(intentBantuan)
                overridePendingTransition(R.anim.no_slide, R.anim.no_slide)
                finish()
            }
            R.id.profile -> {
            }
        }
        return true
    }

    override fun onBackPressed() {
        showAlertDialogFinish()
    }

    private fun showAlertDialogFinish() {
        val builder = AlertDialog.Builder(this@Profile, R.style.AlertDialog)
        val view = LayoutInflater.from(this@Profile).inflate(
                R.layout.dialog_konfirmasi, null
        )
        builder.setView(view)
        val alertDialog = builder.create()
        val iya = view.findViewById<Button>(R.id.butok)
        val tdk = view.findViewById<Button>(R.id.buttdk)
        val pesan = view.findViewById<TextView>(R.id.pesan)
        pesan.text = "Apakah Anda Yakin Ingin Keluar Aplikasi?"
        iya.text = "IYA"
        tdk.text = "TIDAK"
        iya.setOnClickListener {
            alertDialog.cancel()
            finish()
        }
        tdk.setOnClickListener { alertDialog.cancel() }
        if (alertDialog.window != null) {
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
}