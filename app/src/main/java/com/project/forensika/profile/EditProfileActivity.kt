package com.project.forensika.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.project.forensika.R
import com.project.forensika.model.UserProfile
import com.project.forensika.network.ApiConfig
import com.project.forensika.preferences.SharedPreferencesUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : AppCompatActivity() {

    private lateinit var editTextNama: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextRole: EditText

    private lateinit var buttonSimpan: Button
    private lateinit var buttonUbahPassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        editTextNama = findViewById(R.id.et_nama)
        editTextEmail = findViewById(R.id.et_email)
        editTextRole = findViewById(R.id.et_role)

        buttonSimpan = findViewById(R.id.button_simpan)
        buttonUbahPassword = findViewById(R.id.button_ubah_password)

        val sharedPreferencesUtils = SharedPreferencesUtils(this)
        val token = sharedPreferencesUtils.token
        val header = mapOf(
                "Authorization" to token
        )

        buttonSimpan.setOnClickListener {
            val username = editTextNama.text.toString()
            val email = editTextEmail.text.toString()
            val role = editTextRole.text.toString()

            ApiConfig.create().ubahProfile(
                    header = header,
                    username = username,
                    email = email).enqueue(object : Callback<UserProfile?> {
                override fun onResponse(call: Call<UserProfile?>, response: Response<UserProfile?>) {
                    if (response.isSuccessful) {
                        finish()
                    }
                }

                override fun onFailure(call: Call<UserProfile?>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(this@EditProfileActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}