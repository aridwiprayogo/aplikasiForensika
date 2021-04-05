package com.project.forensika

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.project.forensika.home.Home
import java.util.*

@Suppress("DEPRECATION")
class Splash : AppCompatActivity() {

    private lateinit var linlogo: LinearLayout
    private lateinit var animShow: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (Build.VERSION.SDK_INT >= 23) {
            window.statusBarColor = resources.getColor(R.color.hijauatas,theme)
        }
        linlogo = findViewById(R.id.linlogo)
        linlogo.visibility = View.VISIBLE
        animShow = AnimationUtils.loadAnimation(this, R.anim.show)
        linlogo.startAnimation(animShow)
        if (checkAndRequestPermissions()) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (SharedPrefUtils.getBooleanPreference(this@Splash, SharedPrefUtils.IS_LOGIN, false)) {
                    Handler().postDelayed({
                        val i = Intent(this@Splash, Home::class.java)
                        startActivity(i)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                    }, SPLASH_TIME_OUT.toLong())

                } else {
                    Handler().postDelayed({
                        val i = Intent(this@Splash, MainActivity::class.java)
                        startActivity(i)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                    }, SPLASH_TIME_OUT.toLong())
                }
            } else {
                if (SharedPrefUtils.getBooleanPreference(this@Splash, SharedPrefUtils.IS_LOGIN, false)) {
                    Handler().postDelayed({
                        val i = Intent(this@Splash, Home::class.java)
                        startActivity(i)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                    }, SPLASH_TIME_OUT.toLong())
                } else {
                    Handler().postDelayed({
                        val i = Intent(this@Splash, MainActivity::class.java)
                        startActivity(i)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                    }, SPLASH_TIME_OUT.toLong())
                }
            }
        }
    }

    private fun checkAndRequestPermissions(): Boolean {
        val internet = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
        val network = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (internet != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET)
        }
        if (network != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_NETWORK_STATE)
        }
        return if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), 1)
            false
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty()) {
                var sign = ""
                for (i in permissions.indices) {
                    if (permissions[i] == Manifest.permission.INTERNET) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            sign = "Y"
                        } else {
                            sign = "N"
                            val alertDialog2 = AlertDialog.Builder(this@Splash)
                            alertDialog2.setIcon(R.mipmap.ic_launcher_round)
                            alertDialog2.setTitle("Error Aplikasi")
                            alertDialog2.setCancelable(false)
                            alertDialog2.setMessage("Maaf ya...kamu harus memberikan internet agar aplikasi dapat berjalan dengan lancar !")
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
                                alertDialog2.setPositiveButton("OK"
                                ) { _, _ -> checkAndRequestPermissions() }
                            } else {
                                alertDialog2.setPositiveButton("OK"
                                ) { _, _ ->
                                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                    val uri = Uri.fromParts("package", packageName, null)
                                    intent.data = uri
                                    startActivityForResult(intent, 1)
                                    finish()
                                }
                            }
                            alertDialog2.show()
                            break
                        }
                    } else if (permissions[i] == Manifest.permission.ACCESS_NETWORK_STATE) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            sign = "Y"
                        } else {
                            sign = "N"
                            val alertDialog2 = AlertDialog.Builder(this@Splash)
                            alertDialog2.setIcon(R.mipmap.ic_launcher_round)
                            alertDialog2.setTitle("Error Aplikasi")
                            alertDialog2.setCancelable(false)
                            alertDialog2.setMessage("Maaf ya...kamu harus memberikan izin internet agar aplikasi dapat berjalan dengan lancar !")
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_NETWORK_STATE)) {
                                alertDialog2.setPositiveButton("OK"
                                ) { _, _ -> checkAndRequestPermissions() }
                            } else {
                                alertDialog2.setPositiveButton("OK"
                                ) { _, _ ->
                                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                    val uri = Uri.fromParts("package", packageName, null)
                                    intent.data = uri
                                    startActivityForResult(intent, 1)
                                    finish()
                                }
                            }
                            alertDialog2.show()
                            break
                        }
                    }
                }
                if (sign == "Y") {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (SharedPrefUtils.getBooleanPreference(this, SharedPrefUtils.IS_LOGIN, false)) {
                            Handler().postDelayed({
                                val i = Intent(this@Splash, Home::class.java)
                                startActivity(i)
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                finish()
                            }, SPLASH_TIME_OUT.toLong())
                        } else {
                            Handler().postDelayed({
                                val i = Intent(this@Splash, MainActivity::class.java)
                                startActivity(i)
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                finish()
                            }, SPLASH_TIME_OUT.toLong())
                        }
                    } else {
                        if (SharedPrefUtils.getBooleanPreference(this, SharedPrefUtils.IS_LOGIN, false)) {
                            Handler().postDelayed({
                                val i = Intent(this@Splash, Home::class.java)
                                startActivity(i)
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                finish()
                            }, SPLASH_TIME_OUT.toLong())
                        } else {
                            Handler().postDelayed({
                                val i = Intent(this@Splash, MainActivity::class.java)
                                startActivity(i)
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                finish()
                            }, SPLASH_TIME_OUT.toLong())
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val SPLASH_TIME_OUT = 1800
    }
}