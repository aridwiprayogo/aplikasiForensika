package com.project.forensika.checktools

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.forensika.R
import com.project.forensika.history.History
import com.project.forensika.home.Home
import com.project.forensika.model.CheckTools
import com.project.forensika.model.payload.CheckToolsPayload
import com.project.forensika.network.ApiConfig
import com.project.forensika.preferences.SharedPreferencesUtils
import com.project.forensika.profile.Profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckTools : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    //property view related
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var spinnerFunctionality: Spinner
    private lateinit var spinnerMemoryRequirement: Spinner
    private lateinit var spinnerProcessingSpeed: Spinner
    private lateinit var spinnerOutputFormat: Spinner
    private lateinit var spinnerRequiredSkill: Spinner
    private lateinit var spinnerCost: Spinner
    private lateinit var spinnerExamFocus: Spinner
    private lateinit var buttonNext: Button

    //property data check tools
    private var functionality: Int? = null
    private var memoryRequirement: Int? = null
    private var processingSpeed: Int? = null
    private var outputFormat: Int? = null
    private var requiredSkill: Int? = null
    private var cost: Int? = null
    private var examFocus: Int? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_tools)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = resources.getColor(R.color.hijauatas, theme)
        }

        bottomNav = findViewById(R.id.menuNav)
        spinnerFunctionality = findViewById(R.id.spinner_functionality)
        spinnerMemoryRequirement = findViewById(R.id.spinner_memory_requirement)
        spinnerProcessingSpeed = findViewById(R.id.spinner_Processing_speed)
        spinnerOutputFormat = findViewById(R.id.spinner_output_format)
        spinnerRequiredSkill = findViewById(R.id.spinner_required_skill)
        spinnerCost = findViewById(R.id.spinner_cost)
        spinnerExamFocus = findViewById(R.id.spinner_exam_focus)
        buttonNext = findViewById(R.id.btn_hasil)

        bottomNav.menu.findItem(R.id.checkbox_ingat_saya).isChecked = true
        bottomNav.setOnNavigationItemSelectedListener(this)

        setupSpinnerFunctionality()
        setupSpinnerMemoryRequirement()
        setupSpinnerProcessingSpeed()
        setupSpinnerOutputFormat()
        setupSpinnerRequiredSkill()
        setupSpinnerCost()
        setupSpinnerExamFocus()

        buttonNext.setOnClickListener {
            if (functionality == null || functionality == 0) {
                showAlertDialog(getString(R.string.error_missing_message))
            } else if (memoryRequirement == null || memoryRequirement == 0) {
                showAlertDialog(getString(R.string.error_missing_message))
            } else if (processingSpeed == null || processingSpeed == 0) {
                showAlertDialog(getString(R.string.error_missing_message))
            } else if (outputFormat == null || outputFormat == 0) {
                showAlertDialog(getString(R.string.error_missing_message))
            } else if (requiredSkill == null || requiredSkill == 0) {
                showAlertDialog(getString(R.string.error_missing_message))
            } else if (cost == null || cost == 0) {
                showAlertDialog(getString(R.string.error_missing_message))
            } else if (examFocus == null || examFocus == 0) {
                showAlertDialog(getString(R.string.error_missing_message))
            } else {
                val sharedPreferencesUtils = SharedPreferencesUtils(this)
                val token = sharedPreferencesUtils.token
                val headers = mapOf(
                        "Accept" to "application/json",
                        "Authorization" to token,
                )

                ApiConfig.create().checkTools(
                        header = headers,
                    request = CheckToolsPayload(idFungsionalitas = functionality!!,
                        karakteristik = listOf(
                            memoryRequirement!!,
                            processingSpeed!!,
                            outputFormat!!,
                            requiredSkill!!,
                            cost!!,
                            examFocus!!,
                        )
                    )
                ).enqueue(object : Callback<CheckTools?> {
                    override fun onResponse(call: Call<CheckTools?>, response: Response<CheckTools?>) {
                        val checkTools: CheckTools? = response.body()
                        if (checkTools != null && response.isSuccessful) {
                            val elements = checkTools.result
                            val arrayList = ArrayList<CheckTools.Result>(elements)

                            val intent = Intent(this@CheckTools, AplikasiActivity::class.java)
                            intent.putExtra(CHECK_TOOLS_RESULT, arrayList)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<CheckTools?>, t: Throwable) {
                        t.printStackTrace()
                        Toast.makeText(this@CheckTools, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    private fun showAlertDialog(pesanError: String) {
        val builder = AlertDialog.Builder(this@CheckTools, R.style.AlertDialog)
        val view = LayoutInflater.from(this@CheckTools).inflate(
                R.layout.dialog_salah, null
        )
        builder.setView(view)
        val alertDialog = builder.create()
        val pesan = view.findViewById<TextView>(R.id.pesan)
        pesan.text = pesanError
        alertDialog.apply {
            window?.setBackgroundDrawable(ColorDrawable(0))
            setCancelable(true)
            show()
        }
    }

    private fun setupSpinnerFunctionality() {
        val arrayAdapterFunctionality =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.functionality,
                        android.R.layout.simple_spinner_item
                )
        arrayAdapterFunctionality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFunctionality.apply {
            adapter = arrayAdapterFunctionality
            setSelection(1)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> {
                            this@CheckTools.functionality = 0
                        }
                        1 -> {
                            this@CheckTools.functionality = 1
                        }
                        2 -> {
                            this@CheckTools.functionality = 2
                        }
                        3 -> {
                            this@CheckTools.functionality = 3
                        }
                        4 -> {
                            this@CheckTools.functionality = 4
                        }
                        5 -> {
                            this@CheckTools.functionality = 5
                        }
                        6 -> {
                            this@CheckTools.functionality = 6
                        }
                        7 -> {
                            this@CheckTools.functionality = 7
                        }
                        8 -> {
                            this@CheckTools.functionality = 8
                        }
                        9 -> {
                            this@CheckTools.functionality = 9
                        }
                        10 -> {
                            this@CheckTools.functionality = 10
                        }
                        11 -> {
                            this@CheckTools.functionality = 11
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    this@CheckTools.functionality = 0
                }
            }
        }
    }

    private fun setupSpinnerMemoryRequirement() {
        val arrayAdapterFunctionality =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.memory_requirement,
                        android.R.layout.simple_spinner_item
                )

        arrayAdapterFunctionality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMemoryRequirement.apply {
            adapter = arrayAdapterFunctionality
            setSelection(1)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> {
                            this@CheckTools.memoryRequirement = 0
                        }
                        1 -> {
                            this@CheckTools.memoryRequirement = 1
                        }
                        2 -> {
                            this@CheckTools.memoryRequirement = 2
                        }
                        3 -> {
                            this@CheckTools.memoryRequirement = 3
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    this@CheckTools.memoryRequirement = 0

                }
            }
        }
    }

    private fun setupSpinnerProcessingSpeed() {
        val arrayAdapterFunctionality =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.processing_speed,
                        android.R.layout.simple_spinner_item
                )

        arrayAdapterFunctionality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProcessingSpeed.apply {
            adapter = arrayAdapterFunctionality
            setSelection(1)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> {
                            this@CheckTools.processingSpeed = 0
                        }
                        1 -> {
                            this@CheckTools.processingSpeed = 4
                        }
                        2 -> {
                            this@CheckTools.processingSpeed = 5
                        }
                        3 -> {
                            this@CheckTools.processingSpeed = 6
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    this@CheckTools.processingSpeed = 0

                }
            }
        }
    }

    private fun setupSpinnerOutputFormat() {
        val arrayAdapterFunctionality =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.output_format,
                        android.R.layout.simple_spinner_item
                )

        arrayAdapterFunctionality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOutputFormat.apply {
            adapter = arrayAdapterFunctionality
            setSelection(1)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> {
                            this@CheckTools.outputFormat = 0
                        }
                        1 -> {
                            this@CheckTools.outputFormat = 7
                        }
                        2 -> {
                            this@CheckTools.outputFormat = 8
                        }
                        3 -> {
                            this@CheckTools.outputFormat = 9
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    this@CheckTools.outputFormat = 0

                }
            }
        }
    }

    private fun setupSpinnerRequiredSkill() {
        val arrayAdapterFunctionality =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.required_skill,
                        android.R.layout.simple_spinner_item
                )

        arrayAdapterFunctionality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRequiredSkill.apply {
            adapter = arrayAdapterFunctionality
            setSelection(1)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> {
                            this@CheckTools.requiredSkill = 0
                        }
                        1 -> {
                            this@CheckTools.requiredSkill = 10
                        }
                        2 -> {
                            this@CheckTools.requiredSkill = 11
                        }
                        3 -> {
                            this@CheckTools.requiredSkill = 12
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    this@CheckTools.requiredSkill = 0

                }
            }
        }
    }

    private fun setupSpinnerCost() {
        val arrayAdapterFunctionality =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.cost,
                        android.R.layout.simple_spinner_item
                )

        arrayAdapterFunctionality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCost.apply {
            adapter = arrayAdapterFunctionality
            setSelection(1)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> {
                            this@CheckTools.cost = 0
                        }
                        1 -> {
                            this@CheckTools.cost = 13
                        }
                        2 -> {
                            this@CheckTools.cost = 14
                        }
                        3 -> {
                            this@CheckTools.cost = 15
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    this@CheckTools.cost = 0

                }
            }
        }
    }

    private fun setupSpinnerExamFocus() {
        val arrayAdapterFunctionality =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.exam_focus,
                        android.R.layout.simple_spinner_item
                )

        arrayAdapterFunctionality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerExamFocus.apply {
            adapter = arrayAdapterFunctionality
            setSelection(1)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> {
                            this@CheckTools.examFocus = 0
                        }
                        1 -> {
                            this@CheckTools.examFocus = 16
                        }
                        2 -> {
                            this@CheckTools.examFocus = 17
                        }
                        3 -> {
                            this@CheckTools.examFocus = 18
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    this@CheckTools.examFocus = 0

                }
            }
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.awal -> {
                val intent = Intent(this@CheckTools, Home::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.no_slide, R.anim.no_slide)
                finish()
            }
            R.id.history -> {
                val intent = Intent(this@CheckTools, History::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.no_slide, R.anim.no_slide)
                finish()
            }
            R.id.checkbox_ingat_saya -> {
            }
            R.id.profile -> {
                val intent = Intent(this@CheckTools, Profile::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.no_slide, R.anim.no_slide)
                finish()
            }
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this@CheckTools, R.style.AlertDialog)
        val view = LayoutInflater.from(this@CheckTools).inflate(
                R.layout.dialog_konfirmasi, null
        )
        builder.setView(view)
        val alertDialog = builder.create()
        val iya = view.findViewById<Button>(R.id.butok)
        val tdk = view.findViewById<Button>(R.id.buttdk)
        val pesan = view.findViewById<TextView>(R.id.pesan)
        pesan.text = getString(R.string.message_exit_apps)
        iya.text = getString(R.string.yes)
        tdk.text = getString(R.string.no)
        iya.setOnClickListener {
            alertDialog.cancel()
            finish()
        }

        tdk.setOnClickListener { alertDialog.cancel() }
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(0))
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    companion object {
        const val CHECK_TOOLS_RESULT = "check tools result"
    }
}