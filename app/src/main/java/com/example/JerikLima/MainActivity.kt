package com.example.JerikLima

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View) {
        tvInput?.text = ""
    }

    fun onDecimal(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = false
        }
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
        if(tvValue.contains("-")){
            val splitValue = tvValue.split("-")

            var one = splitValue[0]
            var two = splitValue[1]
            if (prefix.isNotEmpty()){
                one = prefix + one
            }
            tvInput?.text = (one.toDouble() - two.toDouble()).toString()
        }
                else if(tvValue.contains("+")){
            val splitValue = tvValue.split("+")

            var one = splitValue[0]
            var two = splitValue[1]
            if (prefix.isNotEmpty()){
                one = prefix + one
            }
            tvInput?.text = (one.toDouble() + two.toDouble()).toString()
        }
        else if(tvValue.contains("*")){
            val splitValue = tvValue.split("*")

            var one = splitValue[0]
            var two = splitValue[1]
            if (prefix.isNotEmpty()){
                one = prefix + one
            }
            tvInput?.text = (one.toDouble() * two.toDouble()).toString()
        }
        else if(tvValue.contains("/")){
            val splitValue = tvValue.split("/")

            var one = splitValue[0]
            var two = splitValue[1]
            if (prefix.isNotEmpty()){
                one = prefix + one
            }
            tvInput?.text = (one.toDouble() / two.toDouble()).toString()
        }

            }catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

}