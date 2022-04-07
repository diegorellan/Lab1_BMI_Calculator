package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelBMI : ViewModel() {
    private var iBmi = MutableLiveData<Double>()
    val bmi: LiveData<Double> = iBmi


    fun countBmi(massvalue: Double, heightvalue: Double){
        var bmi: Double = ((massvalue / (heightvalue * heightvalue)) * 10000)
        iBmi.value = bmi
    }
}