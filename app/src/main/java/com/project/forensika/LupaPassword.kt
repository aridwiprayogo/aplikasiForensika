package com.project.forensika

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class LupaPassword : AppCompatActivity() {
    lateinit var linemail: LinearLayout
    lateinit var linpassword: LinearLayout
    lateinit var edemail: EditText
    lateinit var edsandibaru: EditText
    lateinit var edkonsandi: EditText
    lateinit var butkonfirm: Button
    lateinit var bck: ImageButton

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lupa_password)
        if (Build.VERSION.SDK_INT >= 23) {
            window.statusBarColor = resources.getColor(R.color.hijauatas,theme)
        }
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+"
        linemail = findViewById<LinearLayout>(R.id.linemail)
        linemail.visibility = View.VISIBLE
        linpassword = findViewById<LinearLayout>(R.id.linubhpas)
        linpassword.visibility = View.GONE
        edemail = findViewById<EditText>(R.id.edemail)
        butkonfirm = findViewById<Button>(R.id.konfirm)
        bck = findViewById<ImageButton>(R.id.bck)
        edsandibaru = findViewById<EditText>(R.id.edpassbaru)
        edkonsandi = findViewById<EditText>(R.id.edkonpasbaru)
        butkonfirm.setOnClickListener {
            if (linemail.visibility == View.VISIBLE) {
                if (edemail.text.toString() == "" || edemail.length() == 0) {
                    val builder = AlertDialog.Builder(this@LupaPassword, R.style.AlertDialog)
                    val view = LayoutInflater.from(this@LupaPassword).inflate(
                            R.layout.dialog_salah, null
                    )
                    builder.setView(view)
                    val alertDialog = builder.create()
                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
                    pesan1.text = "Email Tidak Boleh Kosong"
                    if (alertDialog.window != null) {
                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                    }
                    alertDialog.setCancelable(true)
                    alertDialog.show()
                } else if (!edemail.text.toString().matches(emailPattern.toRegex())) {
                    val builder = AlertDialog.Builder(this@LupaPassword, R.style.AlertDialog)
                    val view = LayoutInflater.from(this@LupaPassword).inflate(
                            R.layout.dialog_salah, null
                    )
                    builder.setView(view)
                    val alertDialog = builder.create()
                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
                    pesan1.text = "Email Tidak Valid"
                    if (alertDialog.window != null) {
                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                    }
                    alertDialog.setCancelable(true)
                    alertDialog.show()
                } else {
                    linemail.visibility = View.GONE
                    linpassword.visibility = View.VISIBLE
                }
            } else {
                if (edsandibaru.text.toString() == "" || edsandibaru.length() == 0) {
                    val builder = AlertDialog.Builder(this@LupaPassword, R.style.AlertDialog)
                    val view = LayoutInflater.from(this@LupaPassword).inflate(
                            R.layout.dialog_salah, null
                    )
                    builder.setView(view)
                    val alertDialog = builder.create()
                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
                    pesan1.text = "Sandi Baru Tidak Boleh Kosong"
                    if (alertDialog.window != null) {
                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                    }
                    alertDialog.setCancelable(true)
                    alertDialog.show()
                } else if (edkonsandi.text.toString() == "" || edkonsandi.length() == 0) {
                    val builder = AlertDialog.Builder(this@LupaPassword, R.style.AlertDialog)
                    val view = LayoutInflater.from(this@LupaPassword).inflate(
                            R.layout.dialog_salah, null
                    )
                    builder.setView(view)
                    val alertDialog = builder.create()
                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
                    pesan1.text = "Konfirmasi Kata Sandi Tidak Boleh Kosong"
                    if (alertDialog.window != null) {
                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                    }
                    alertDialog.setCancelable(true)
                    alertDialog.show()
                } else if (edsandibaru.text.toString() != edkonsandi.text.toString()) {
                    val builder = AlertDialog.Builder(this@LupaPassword, R.style.AlertDialog)
                    val view = LayoutInflater.from(this@LupaPassword).inflate(
                            R.layout.dialog_salah, null
                    )
                    builder.setView(view)
                    val alertDialog = builder.create()
                    val pesan1 = view.findViewById<TextView>(R.id.pesan)
                    pesan1.text = "Kata Sandi Tidak Sama"
                    if (alertDialog.window != null) {
                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                    }
                    alertDialog.setCancelable(true)
                    alertDialog.show()
                } else {
                    val intentrwyt = Intent(this@LupaPassword, MainActivity::class.java)
                    intentrwyt.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intentrwyt)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.no_slide)
                    finish()
                }
            }
        }
        butkonfirm.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val view = v as Button
                    view.background.setColorFilter(resources.getColor(R.color.abumuda), PorterDuff.Mode.SRC_ATOP)
                    view.invalidate()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    val view = v as Button
                    view.background.clearColorFilter()
                    view.invalidate()
                }
            }
            false
        }
        bck.setOnClickListener { onBackPressed() }
        bck.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val view = v as ImageButton
                    view.background.setColorFilter(resources.getColor(R.color.abumuda), PorterDuff.Mode.SRC_ATOP)
                    view.invalidate()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    val view = v as ImageButton
                    view.background.clearColorFilter()
                    view.invalidate()
                }
            }
            false
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_slide, R.anim.slide_out_right)
    }
}