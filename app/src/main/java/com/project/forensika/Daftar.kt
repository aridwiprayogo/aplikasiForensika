package com.project.forensika

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.project.forensika.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.Map

class Daftar : AppCompatActivity() {
    private lateinit var user       : EditText
    private lateinit var email      : EditText
    private lateinit var sandi      : EditText
    private lateinit var konsandi   : EditText
    private lateinit var daftar     : Button
    private lateinit var chex       : CheckBox
    private lateinit var bck        : ImageButton
    private lateinit var pesan      : String
    private lateinit var success    : String

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)
        if (Build.VERSION.SDK_INT >= 21) {
            window.statusBarColor = resources.getColor(R.color.hijauatas, theme)
        }
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+"
        success = ""
        pesan = ""
        user = findViewById(R.id.textview_user)
        email = findViewById(R.id.em)
        sandi = findViewById(R.id.textview_sandi)
        konsandi = findViewById(R.id.konKatasandi)
        daftar = findViewById(R.id.daftar)
        chex = findViewById(R.id.checkbox_ingat_saya)
        bck = findViewById(R.id.bck)
        daftar.setOnClickListener {
            val username = user.text.toString()
            val email = email.text.toString()
            val sandi = sandi.text.toString()
            val konsandi = konsandi.text.toString()

            if (username == "" || user.length() == 0) {
                showAlertDialog("Username Tidak Boleh Kosong")
            } else if (email == "" || this.email.length() == 0) {
                val error = "Email Tidak Boleh Kosong"
                showAlertDialog(error)
            } else if (!email.matches(emailPattern.toRegex())) {
                val error = "Email Tidak Valid"
                showAlertDialog(error)
            } else if (sandi == "" || this.sandi.length() == 0) {
                val error = "Kata Sandi Tidak Boleh Kosong"
                showAlertDialog(error)
            } else if (konsandi == "" || this.konsandi.length() == 0) {
                showAlertDialog("Konfirmasi Kata Sandi Tidak Boleh Kosong")
            } else if (konsandi != sandi) {
                showAlertDialog("Konfirmasi Kata Sandi Tidak Boleh Kosong")
            } else if (!chex.isChecked) {
                showAlertDialog("Anda Belum Menyetujui Syarat dan Ketentuan")
            } else {
                val apiService = ApiConfig.create()
                apiService.register(username, email, sandi, konsandi).enqueue(object : Callback<Map<String,Any>> {
                    override fun onResponse(call: Call<Map<String,Any>>, response: Response<Map<String,Any>>) {
                        val intent = Intent(this@Daftar, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    }

                    override fun onFailure(call: Call<Map<String,Any>>, t: Throwable) {
                        Log.e(TAG, "onFailure: ", t)
                        t.localizedMessage?.let{ showAlertDialog(error = it) }
                    }

                })
            }
        }

        bck.setOnClickListener { onBackPressed() }
    }

    private fun showAlertDialog(error: String) {
        val builder = AlertDialog.Builder(this@Daftar, R.style.AlertDialog)
        val view = LayoutInflater.from(this@Daftar).inflate(
                R.layout.dialog_salah, null
        )
        builder.setView(view)
        val alertDialog = builder.create()
        val pesan1 = view.findViewById<TextView>(R.id.pesan)
        pesan1.text = error
        if (alertDialog.window != null) {
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    /*@SuppressLint("StaticFieldLeak")
    inner class Register : AsyncTask<String?, String?, String?>() {

        lateinit var pDialog: ProgressDialog

        override fun onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute()
            pDialog = ProgressDialog(this@Daftar)
            pDialog.setMessage("Mohon ditunggu...")
            pDialog.isIndeterminate = true
            pDialog.setCancelable(false)
            pDialog.show()
        }

        //        @SuppressLint("WrongThread")
        override fun doInBackground(vararg params: String?): String? {
            val param: MutableList<NameValuePair> = ArrayList()
            param.add(BasicNameValuePair("name", user.text.toString()))
            param.add(BasicNameValuePair("email", email.text.toString()))
            param.add(BasicNameValuePair("password", sandi.text.toString()))
            param.add(BasicNameValuePair("password_corfirmation", konsandi.text.toString()))
            val jParser = JSONParser()
            val json = jParser.makeHttpRequest("https//localhost:8000/api/register", "POST", param)
            try {
                success = json.getString("status") //ini dari hasil responmu,buat penanda berhasil apa gagal
                pesan = json.getString("message") // ini juga dari respon
            } catch (e: Exception) {
                // TODO: handle exception
                success = "Error"
            }
            return null
        }

        @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pDialog.dismiss()
            if (success == "Ok") { //ini oknya diganti sesuai sama responmu,kalo berhasil apa indicatornya
                val intentrwyt = Intent(this@Daftar, MainActivity::class.java)
                intentrwyt.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intentrwyt)
                overridePendingTransition(R.anim.slide_in_right, R.anim.no_slide)
                finish()

                //ini aku buat kalo berhasil dia balik ke halaman awal
            } else {
                val builder = AlertDialog.Builder(this@Daftar, R.style.AlertDialog)
                val view = LayoutInflater.from(this@Daftar).inflate(
                        R.layout.dialog_salah, null
                )
                builder.setView(view)
                val alertDialog = builder.create()
                val pesan1 = view.findViewById<TextView>(R.id.pesan)
                pesan1.text = pesan
                if (alertDialog.window != null) {
                    alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                }
                alertDialog.setCancelable(true)
                alertDialog.show()
            }
        }
    }*/

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_slide, R.anim.slide_out_right)
    }

    companion object {
        private val TAG = Daftar::class.simpleName
    }
}