package com.example.organic.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.example.organic.R
import com.example.organic.dao.AppDatabase
import com.example.organic.entity.Product
import com.google.android.material.textfield.TextInputEditText


class RegisterProductActivity : AppCompatActivity() {
    var database: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_product)

        val actionBar = supportActionBar

        database = AppDatabase.getInstance(this)
        actionBar!!.title = "Cadastro de Produto"
        actionBar.setDisplayHomeAsUpEnabled(true)

//        val button = findViewById<Button>(R.id.button)

//        button.setOnClickListener {
//            val product = Product()
//            val intent = Intent(this, MainActivity::class.java)
//
//            product.name = findViewById<TextInputEditText>(R.id.textInputName).text.toString()
////            product.description = findViewById<TextInputEditText>(R.id.textInputDescription).text.toString()
////            product.amountValue = findViewById<TextInputEditText>(R.id.textInputAmountValue).text.toString().toDouble()
////            product.providerName = findViewById<TextInputEditText>(R.id.textInputProviderName).text.toString()
//
//            database!!.productDAO().insert(product)
//            startActivity(intent)
//            limparCampos()
//            Toast.makeText(applicationContext, "Produto foi cadastrado com sucesso!", Toast.LENGTH_LONG).show()
//
//        }
    }

    fun limparCampos() {
        findViewById<AppCompatEditText>(R.id.editTextName).setText("")
        findViewById<AppCompatEditText>(R.id.editTextDescription).setText("")
        findViewById<AppCompatEditText>(R.id.editTextAmountValue).setText("")
        findViewById<AppCompatEditText>(R.id.editTextProviderName).setText("")
    }
}