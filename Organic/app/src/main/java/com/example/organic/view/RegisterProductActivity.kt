package com.example.organic.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.example.organic.R
import com.example.organic.dao.AppDatabase
import com.example.organic.entity.Product
import com.google.android.material.snackbar.Snackbar
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

        val button = findViewById<Button>(R.id.registerButton)

        button.setOnClickListener { view ->
            val product = Product()

            val name = findViewById<TextInputEditText>(R.id.textInputName)
            val description = findViewById<TextInputEditText>(R.id.textInputDescription)
            val amountValue = findViewById<TextInputEditText>(R.id.textInputAmountValue)
            val providerName = findViewById<TextInputEditText>(R.id.textInputProviderName)

            product.name = name.text.toString()
            product.description = description.text.toString()
            product.amountValue = amountValue?.text.toString().toDoubleOrNull()
            product.providerName = providerName.text.toString()

            val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
            snackBar.setBackgroundTint(Color.RED)
            snackBar.setAction("OK", View.OnClickListener { snackBar.dismiss() })

            if (name.text.toString().trim { it <= ' ' } == "") {
                snackBar.setText("Nome é obrigatório!").show()
                name?.requestFocus()
            }
            else if (description.text.toString().trim { it <= ' ' } == "") {
                snackBar.setText("Descrição é obrigatório!").show()
                description?.requestFocus()
            } else if (amountValue?.text.toString().trim { it <= ' ' } == "") {
                snackBar.setText("Preço é obrigatório!").show()
                amountValue?.requestFocus()
            } else if (providerName?.text.toString().trim { it <= ' ' } == "") {
                snackBar.setText("Nome do fornecedor é obrigatório!").show()
                providerName?.requestFocus()
            }
            else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("registerSuccess", true)

                database!!.productDAO().insert(product)
                limparCampos()
                startActivity(intent)
            }
        }
    }

    fun limparCampos() {
        findViewById<AppCompatEditText>(R.id.textInputName).setText("")
        findViewById<AppCompatEditText>(R.id.textInputDescription).setText("")
        findViewById<AppCompatEditText>(R.id.textInputAmountValue).setText("")
        findViewById<AppCompatEditText>(R.id.textInputProviderName).setText("")
    }
}