package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.widget.TextView

class activity_bmi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)
        val resultBMI = intent.getDoubleExtra(EXTRA_MESSAGE,0.0)
        val textView = findViewById<TextView>(R.id.value).apply {
            text = resultBMI.toString()
        }

        if (resultBMI<18.5){
            val text = findViewById<android.widget.TextView>(R.id.classification)
            val desc = findViewById<android.widget.TextView>(R.id.description)
            text.setTextColor(Color.parseColor("#2657AC"))
            text.text="Underweight"
            desc.text="Being underweight can represent as much risk as being overweight. Try to take care."
        }
        if ((24.9>resultBMI)&&(resultBMI>18.5)){
            val text = findViewById<android.widget.TextView>(R.id.classification)
            val desc = findViewById<android.widget.TextView>(R.id.description)
            text.setTextColor(Color.parseColor("#209107"))
            text.text="Healthy weight"
            desc.text="Good job! Keep being healthy."
        }
        if ((29.9>resultBMI)&&(resultBMI>25)){
            val text = findViewById<android.widget.TextView>(R.id.classification)
            val desc = findViewById<android.widget.TextView>(R.id.description)
            text.setTextColor(Color.parseColor("#C62C2C"))
            text.text="Overweight"
            desc.text="You should look out for your weight."
        }
        if (resultBMI>30){
            val text = findViewById<android.widget.TextView>(R.id.classification)
            val desc = findViewById<android.widget.TextView>(R.id.description)
            text.setTextColor(Color.parseColor("#FF0000"))
            text.text="Obese"
            desc.text="You are in risk. You need to improve your health."
        }
    }
}