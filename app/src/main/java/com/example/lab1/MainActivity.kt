package com.example.lab1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


var conversionNumber=10000.0
const val EXTRA_MESSAGE = "com.example.app.lab1"
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ViewModelBMI

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ViewModelBMI::class.java)
        viewModel.bmi.observe(this) { bmi ->
            val bminumber = findViewById<TextView>(R.id.bmi_value)
        }
        val bminumber = findViewById<TextView>(R.id.bmi_value)


            // finding the button
            val showButton = findViewById<android.widget.Button>(R.id.button)
            // finding the edit text
            val massEditText = findViewById<EditText>(R.id.massEditText)
            val heightEditText = findViewById<EditText>(R.id.heightEditText)
            // Setting On Click Listener for the button
            showButton.setOnClickListener {


                // Getting the user input
                val massText: Double = massEditText.getText().toString().toDouble()
                val heightText: Double = heightEditText.getText().toString().toDouble()

                //Getting the values for the ViewModel
                viewModel.countBmi(massText, heightText)

                // Computing the value of the BMI
                val classBMI = BMI(massText = massText, heightText = heightText, conversionNumber)
                // Getting the result of the BMI
                val resultBMI = classBMI.resultBMI
                //Printing the value
                findViewById<android.widget.TextView>(R.id.bmi_value).text = resultBMI.toString()
                //Changing the background color
                if (resultBMI < 18.5) {
                    val text = findViewById<android.widget.TextView>(R.id.bmi_value)
                    text.setTextColor(Color.parseColor("#2657AC"))
                }
                if ((24.9 > resultBMI) && (resultBMI > 18.5)) {
                    val text = findViewById<android.widget.TextView>(R.id.bmi_value)
                    text.setTextColor(Color.parseColor("#209107"))
                }
                if ((29.9 > resultBMI) && (resultBMI > 25)) {
                    val text = findViewById<android.widget.TextView>(R.id.bmi_value)
                    text.setTextColor(Color.parseColor("#C62C2C"))
                }
                if (resultBMI > 30) {
                    val text = findViewById<android.widget.TextView>(R.id.bmi_value)
                    text.setTextColor(Color.parseColor("#FF0000"))
                }
                fun demo() {
                }

                //Setting onClickListener for the TextView
                val click_bmi_value = findViewById(R.id.bmi_value) as TextView
                click_bmi_value.setOnClickListener {
                    val intent2 = Intent(this, activity_bmi::class.java).apply {
                        putExtra(EXTRA_MESSAGE, resultBMI)
                    }
                    startActivity(intent2)
                }

            }
            //Setting the ViewModel
            //val viewModel: AppViewModel by viewModels()

        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            val inflater: MenuInflater = menuInflater
            inflater.inflate(R.menu.options_menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle item selection
            return when (item.itemId) {
                R.id.imperial -> {
                    conversionNumber = 703.0
                    findViewById<android.widget.TextView>(R.id.mass_text).text = "Mass [lbs]"
                    findViewById<android.widget.TextView>(R.id.height_text).text = "Height [inches]"
                    true
                }
                R.id.metric -> {
                    conversionNumber = 10000.0
                    findViewById<android.widget.TextView>(R.id.mass_text).text = "Mass [Kg]"
                    findViewById<android.widget.TextView>(R.id.height_text).text = "Height [cm]"
                    true
                }
                R.id.about -> {
                    val intent = Intent(this, MenuAboutActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }

    }

    open class BMI(val massText: Double, val heightText: Double, var conversionNumber: Double) {
        val resultBMI = ((massText / heightText / heightText) * conversionNumber)

    }



