package com.project.forensika.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.project.forensika.R
import com.project.forensika.model.ResponseMessage
import com.project.forensika.network.ApiConfig
import com.project.forensika.preferences.SharedPreferencesUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPasswordProfileActivity : AppCompatActivity() {

    private lateinit var editTextPasswordLama: EditText
    private lateinit var editTextPasswordBaru: EditText
    private lateinit var editTextKonfirmasiPassword: EditText
    private lateinit var buttonSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_password_profile)

        editTextPasswordLama = findViewById(R.id.et_password_lama)
        editTextPasswordBaru = findViewById(R.id.et_password_baru)
        editTextKonfirmasiPassword = findViewById(R.id.et_konfirmasi_password)
        buttonSimpan = findViewById(R.id.button_simpan)

        val sharedPreferencesUtils = SharedPreferencesUtils(this)
        val token = sharedPreferencesUtils.token
        val header = mapOf(
                "Authorization" to token
        )

        buttonSimpan.setOnClickListener {
            val passwordLama = editTextPasswordLama.text.toString()
            val passwordBaru = editTextPasswordBaru.text.toString()
            val konfirmasiPassword = editTextKonfirmasiPassword.text.toString()

            ApiConfig.create().ubahPasswordProfile(
                    header = header,
                    currentPassword = passwordLama,
                    password = passwordBaru,
                    konfirmasiPassword = konfirmasiPassword
                    ).enqueue(object : Callback<ResponseMessage?> {
                override fun onResponse(call: Call<ResponseMessage?>, response: Response<ResponseMessage?>) {
                    if (response.isSuccessful) {
                        val responseMessage = response.body()
                        Toast.makeText(this@EditPasswordProfileActivity, responseMessage?.message, Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<ResponseMessage?>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(this@EditPasswordProfileActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}