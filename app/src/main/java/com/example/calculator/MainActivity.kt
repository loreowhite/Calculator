package com.example.calculator

import ExpressionParser
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var outputExpression: TextView
    private lateinit var outputMain: TextView

    private var expression = ""
    private var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        outputExpression = findViewById(R.id.output_expression)
        outputMain = findViewById(R.id.output_main)

        val buttons = listOf(
            findViewById<Button>(R.id.btn_one),
            findViewById<Button>(R.id.btn_two),
            findViewById<Button>(R.id.btn_three),
            findViewById<Button>(R.id.btn_four),
            findViewById<Button>(R.id.btn_five),
            findViewById<Button>(R.id.btn_six),
            findViewById<Button>(R.id.btn_seven),
            findViewById<Button>(R.id.btn_eight),
            findViewById<Button>(R.id.btn_nine),
            findViewById<Button>(R.id.btn_zero),
            findViewById<Button>(R.id.btn_comma)
        )

        val operation = listOf(
            findViewById<Button>(R.id.btn_plus),
            findViewById<Button>(R.id.btn_minus),
            findViewById<Button>(R.id.btn_div),
            findViewById<Button>(R.id.btn_mul)
        )
        operation.forEach{it.setOnClickListener{_-> operatorClick(it.text.toString())}}

        val btnClear = findViewById<Button>(R.id.btn_clear)
        val btnDelete = findViewById<Button>(R.id.btn_delete)
        val btnEquals = findViewById<Button>(R.id.btn_equals)

        for (btn in buttons) {
            btn.setOnClickListener { onNumberClick(btn.text.toString()) }
        }

        btnClear.setOnClickListener { onClearClick() }
        btnDelete.setOnClickListener { onDeleteClick() }
        btnEquals.setOnClickListener { onEqualsClick() }
    }

    private fun onNumberClick(value: String) {
        expression += value
        updateOutput()
    }

    private fun operatorClick(value: String){
        expression = "$expression $value "
        updateOutput()
    }

    private fun onClearClick() {
        expression = ""
        result = ""
        updateOutput()
    }

    private fun onDeleteClick() {
        if (expression.isNotEmpty()) {
            expression = expression.substring(0, expression.length - 1)
            updateOutput()
        }
    }

    private fun onEqualsClick() {
        try {
            val parser = ExpressionParser()
            val calculatedResult = parser.evaluate(expression)
            result = calculatedResult.toString()
            expression = result
        } catch (e: Exception) {
            result = "Error"
        }
        updateOutput()
    }


    private fun updateOutput() {
        // Обновление текста на экране
        outputExpression.text = expression
        outputMain.text = result
    }
}