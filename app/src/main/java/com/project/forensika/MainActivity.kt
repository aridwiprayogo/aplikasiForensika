package com.project.forensika

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.project.forensika.*
import com.project.forensika.home.Home
import com.project.forensika.model.UserResponse
import com.project.forensika.network.ApiConfig
import com.project.forensika.preferences.SharedPreferencesUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var checkBox: CheckBox
    private lateinit var masuk: Button
    private lateinit var user: EditText
    private lateinit var sandi: EditText
    private lateinit var lupa: TextView
    private lateinit var textViewDaftar: TextView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= 23) {
            window.statusBarColor = resources.getColor(R.color.hijauatas, theme)
        }
        checkBox = findViewById(R.id.checkbox_ingat_saya)
        masuk = findViewById(R.id.masuk)
        user = findViewById(R.id.textview_user)
        sandi = findViewById(R.id.textview_sandi)
        lupa = findViewById(R.id.lupa)
        textViewDaftar = findViewById(R.id.dftr)
        lupa.setOnClickListener {
            val a = Intent(this@MainActivity, LupaPassword::class.java)
            startActivity(a)
            overridePendingTransition(R.anim.slide_in_right, R.anim.no_slide)
        }

        masuk.setOnClickListener {
            val sandi = sandi.text.toString()
            val username = user.text.toString()

            if (username == "" || user.length() == 0) {
                showAlertDialog(getString(R.string.error_username_empty))
            } else if (sandi == "" || this.sandi.length() == 0) {
                showAlertDialog(getString(R.string.error_password_empty))
            } else if (checkBox.isChecked) {
                SharedPrefUtils.setBooleanPreference(this@MainActivity, SharedPrefUtils.IS_LOGIN, true)
                pindahHalamanHome()
            } else {
                ApiConfig.create().login(username,sandi).enqueue(object : Callback<UserResponse> {
                    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                        val userResponse = response.body()
                        if (response.isSuccessful && userResponse != null) {
                            val sharedPrefUtils = SharedPreferencesUtils(this@MainActivity)
                            sharedPrefUtils.token = userResponse.accessToken
                            pindahHalamanHome()
                            call.cancel()
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        t.printStackTrace()
                        Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                        call.cancel()
                    }

                })
            }
        }

        textViewDaftar.setOnClickListener {
            val intent = Intent(this@MainActivity, Daftar::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.no_slide)
        }

    }

    private fun pindahHalamanHome() {
        val h = Intent(this@MainActivity, Home::class.java)
        startActivity(h)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        finish()
    }

    private fun showAlertDialog(errorMessage: String) {
        val builder = AlertDialog.Builder(this@MainActivity, R.style.AlertDialog)
        val inflater = LayoutInflater.from(this@MainActivity)
        val view = inflater.inflate(R.layout.dialog_salah, null)
        builder.setView(view)
        val alertDialog = builder.create()
        val pesan1 = view.findViewById<TextView>(R.id.pesan)
        pesan1.text = errorMessage
        if (alertDialog.window != null) {
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}