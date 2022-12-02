package com.rompos.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput : TextView? = null

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    //private var btnOne : Button? = null //another way of doing the functions onDigit , OnClear ect...
    //private var btnTwo : Button? = null //etc...
    //private var btnThree : Button? = null //etc...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
        //btnOne = findViewById(R.id.btnOne) //etc...
        //btnOne?.setOnClickListener { tvInput?.append("1") } //etc...
        //btnTwo = findViewById(R.id.btnTwo) //etc...
        //btnTwo?.setOnClickListener { tvInput?.append("2") } //etc...
        //btnThree = findViewById(R.id.btnThree) //etc...
        //btnThree?.setOnClickListener { tvInput?.append("3") } //etc...

    }

    fun onDigit(view : View){
        //Toast.makeText(this,"Button clicked",Toast.LENGTH_LONG).show()
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false

        var sentence = "Stratos is a coder"
//        if(sentence.contains("Stratos")){
//            tvInput?.append("Stratos")
//        }
    }

    fun onClear(view : View){
        tvInput?.text = ""
    }

    fun onDecimalPoint(view : View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view : View){
        //nullable chaining with lambda let operator
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view : View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                    //if it was -99 then we get 99
                }
                //---------------------------Subtract---------------------------------------
                if(tvValue.contains("-")) {
                    var splitValue = tvValue.split("-")
                    //99-1 -> 1st entry 99 and second entry 1
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    //-----------------------------Add------------------------------------
                }else if(tvValue.contains("+")) {
                    var splitValue = tvValue.split("+")
                    //99-1 -> 1st entry 99 and second entry 1
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    //---------------------------Multiply-----------------------------------------
                }else if(tvValue.contains("*")) {
                    var splitValue = tvValue.split("*")
                    //99-1 -> 1st entry 99 and second entry 1
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    //-----------------------------Divide-------------------------------
                }else if(tvValue.contains("/")) {
                    var splitValue = tvValue.split("/")
                    //99-1 -> 1st entry 99 and second entry 1
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            }catch(e : ArithmeticException){
                //example divide by zero
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result : String) : String {
        var value = result
        if(result.contains(".0")){
            value = result.substring(0,result.length - 2)
        }
        return value
    }

    private fun isOperatorAdded(value : String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")//returns a boolean
                    ||value.contains("*")
                    ||value.contains("+")
                    ||value.contains("-")
        }
    }

}