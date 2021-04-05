package com.project.forensika

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.forensika.history.History
import com.project.forensika.home.Home

class Profile : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var butedit: Button
    private lateinit var butubhpas: Button
    private lateinit var keluar: RelativeLayout
    private lateinit var txnama: TextView
    private lateinit var txemail: TextView
    private lateinit var txrole: TextView
    private lateinit var ednama: EditText
    private lateinit var edemail: EditText
    private lateinit var edrole: EditText
    private lateinit var edpaslama: EditText
    private lateinit var edpasbaru: EditText
    private lateinit var edkonpasbaru: EditText
    private lateinit var linprofil: LinearLayout
    private lateinit var linpass: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        if (Build.VERSION.SDK_INT >= 23) {
            window.statusBarColor = resources.getColor(R.color.hijauatas,theme)
        }

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        bottomNav = findViewById(R.id.menuNav)
        bottomNav.menu.findItem(R.id.profile).isChecked = true
        bottomNav.setOnNavigationItemSelectedListener(this)
        butedit = findViewById(R.id.butedit)
        butubhpas = findViewById(R.id.butgntpas)
        linprofil = findViewById(R.id.linedtprofil)
        linprofil.visibility = View.VISIBLE
        txnama = findViewById(R.id.txnama)
        txnama.hint = "Isinama"
        txemail = findViewById(R.id.txemail)
        txemail.hint = "Isiemail"
        txrole = findViewById(R.id.txrole)
        txrole.hint = "Isirole"
        ednama = findViewById(R.id.ednama)
        ednama.visibility = View.GONE
        edemail = findViewById(R.id.edemail)
        edemail.visibility = View.GONE
        edrole = findViewById(R.id.edrole)
        edrole.visibility = View.GONE
        linpass = findViewById(R.id.linubhpas)
        linpass.visibility = View.GONE
        edpaslama = findViewById(R.id.edpasslama)
        edpasbaru = findViewById(R.id.edpassbaru)
        edkonpasbaru = findViewById(R.id.edkonpasbaru)
        keluar = findViewById(R.id.keluar)

        butedit.setOnClickListener {
            if (butedit.text.toString() == "EDIT") {
                if (butubhpas.text.toString() == "SIMPAN") {
                    butubhpas.text = "UBAH PASSWORD"
                    linpass.visibility = View.GONE
//                    linprofil.visibility = View.VISIBLE
                }
                val nama  = txnama.text.toString()
                val email = txemail.text.toString()
                val role  = txrole.text.toString()
                txnama.visibility = View.GONE
                txemail.visibility = View.GONE
                txrole.visibility = View.GONE
//                ednama.visibility = View.VISIBLE
//                ednama.setText(nama)
//                edemail.visibility = View.VISIBLE
//                edemail.setText(email)
//                edrole.visibility = View.VISIBLE
//                edrole.setText(role)
                butedit.text = "SIMPAN"
            } else {
//                if (ednama.text.toString() == "" || ednama.length() == 0) {
//                    val builder = AlertDialog.Builder(this@Profile, R.style.AlertDialog)
//                    val view = LayoutInflater.from(this@Profile).inflate(
//                            R.layout.dialog_salah, null
//                    )
//                    builder.setView(view)
//                    val alertDialog = builder.create()
//                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
//                    pesan1.text = "Nama Tidak Boleh Kosong"
//                    if (alertDialog.window != null) {
//                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
//                    }
//                    alertDialog.setCancelable(true)
//                    alertDialog.show()
//                } else if (edemail.text.toString() == "" || edemail.length() == 0) {
//                    val builder = AlertDialog.Builder(this@Profile, R.style.AlertDialog)
//                    val view = LayoutInflater.from(this@Profile).inflate(
//                            R.layout.dialog_salah, null
//                    )
//                    builder.setView(view)
//                    val alertDialog = builder.create()
//                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
//                    pesan1.text = "Email Tidak Boleh Kosong"
//                    if (alertDialog.window != null) {
//                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
//                    }
//                    alertDialog.setCancelable(true)
//                    alertDialog.show()
//                } else if (!edemail.text.toString().matches(emailPattern.toRegex())) {
//                    val builder = AlertDialog.Builder(this@Profile, R.style.AlertDialog)
//                    val view = LayoutInflater.from(this@Profile).inflate(
//                            R.layout.dialog_salah, null
//                    )
//                    builder.setView(view)
//                    val alertDialog = builder.create()
//                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
//                    pesan1.text = "Email Tidak Valid"
//                    if (alertDialog.window != null) {
//                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
//                    }
//                    alertDialog.setCancelable(true)
//                    alertDialog.show()
//                } else if (edrole.text.toString() == "" || edrole.length() == 0) {
//                    val builder = AlertDialog.Builder(this@Profile, R.style.AlertDialog)
//                    val view = LayoutInflater.from(this@Profile).inflate(
//                            R.layout.dialog_salah, null
//                    )
//                    builder.setView(view)
//                    val alertDialog = builder.create()
//                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
//                    pesan1.text = "Role Tidak Boleh Kosong"
//                    if (alertDialog.window != null) {
//                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
//                    }
//                    alertDialog.setCancelable(true)
//                    alertDialog.show()
//                } else {
//                    val isinama = ednama.text.toString()
//                    val isiemail = edemail.text.toString()
//                    val isirole = edrole.text.toString()
//                    ednama.visibility = View.GONE
//                    edemail.visibility = View.GONE
//                    edrole.visibility = View.GONE
//                    txnama.visibility = View.VISIBLE
//                    txnama.text = isinama
//                    txemail.visibility = View.VISIBLE
//                    txemail.text = isiemail
//                    txrole.visibility = View.VISIBLE
//                    txrole.text = isirole
//                    butedit.text = "EDIT"
//                }
            }
        }
        butubhpas.setOnClickListener {
            if (butubhpas.text.toString() == "UBAH PASSWORD") {
                if (butedit.text.toString() == "SIMPAN") {
                    butedit.text = "EDIT"
                }
//                linprofil.visibility = View.GONE
                linpass.visibility = View.VISIBLE
                butubhpas.text = "SIMPAN"
            } else {
//                if (edpaslama.text.toString() == "" || edpaslama.length() == 0) {
//                    val builder = AlertDialog.Builder(this@Profile, R.style.AlertDialog)
//                    val view = LayoutInflater.from(this@Profile).inflate(
//                            R.layout.dialog_salah, null
//                    )
//                    builder.setView(view)
//                    val alertDialog = builder.create()
//                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
//                    pesan1.text = "Password Lama Tidak Boleh Kosong"
//                    if (alertDialog.window != null) {
//                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
//                    }
//                    alertDialog.setCancelable(true)
//                    alertDialog.show()
//                } else if (edpasbaru.text.toString() == "" || edpasbaru.length() == 0) {
//                    val builder = AlertDialog.Builder(this@Profile, R.style.AlertDialog)
//                    val view = LayoutInflater.from(this@Profile).inflate(
//                            R.layout.dialog_salah, null
//                    )
//                    builder.setView(view)
//                    val alertDialog = builder.create()
//                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
//                    pesan1.text = "Password Baru Tidak Boleh Kosong"
//                    if (alertDialog.window != null) {
//                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
//                    }
//                    alertDialog.setCancelable(true)
//                    alertDialog.show()
//                } else if (edkonpasbaru.text.toString() == "" || edkonpasbaru.length() == 0) {
//                    val builder = AlertDialog.Builder(this@Profile, R.style.AlertDialog)
//                    val view = LayoutInflater.from(this@Profile).inflate(
//                            R.layout.dialog_salah, null
//                    )
//                    builder.setView(view)
//                    val alertDialog = builder.create()
//                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
//                    pesan1.text = "Konfirmasi Password Baru Tidak Boleh Kosong"
//                    if (alertDialog.window != null) {
//                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
//                    }
//                    alertDialog.setCancelable(true)
//                    alertDialog.show()
//                } else if (edpasbaru.text.toString() != edkonpasbaru.text.toString()) {
//                    val builder = AlertDialog.Builder(this@Profile, R.style.AlertDialog)
//                    val view = LayoutInflater.from(this@Profile).inflate(
//                            R.layout.dialog_salah, null
//                    )
//                    builder.setView(view)
//                    val alertDialog = builder.create()
//                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
//                    pesan1.text = "Password Baru Tidak Cocok"
//                    if (alertDialog.window != null) {
//                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
//                    }
//                    alertDialog.setCancelable(true)
//                    alertDialog.show()
//                } else {
//                    butubhpas.text = "UBAH PASSWORD"
//                    linpass.visibility = View.GONE
//                    linprofil.visibility = View.VISIBLE
//                }
            }
        }
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