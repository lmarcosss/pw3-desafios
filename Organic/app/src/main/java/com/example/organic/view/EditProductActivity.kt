package com.example.organic.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.organic.R
import com.example.organic.dao.AppDatabase
import com.example.organic.dao.ProductDAO
import com.example.organic.entity.Product
import com.google.android.material.snackbar.Snackbar

class EditProductActivity : AppCompatActivity() {
    var database: AppDatabase? = null
    var editTextName: AppCompatEditText? = null
    var editTextDescription: AppCompatEditText? = null
    var editTextAmountValue: AppCompatEditText? = null
    var editTextProviderName: AppCompatEditText? = null
    var product: Product? = null
    var editButton: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        val actionBar = supportActionBar

        database = AppDatabase.getInstance(this)
        actionBar!!.title = "Edição do Produto"
        actionBar.setDisplayHomeAsUpEnabled(true)

        editTextName = findViewById<AppCompatEditText>(R.id.textEditInputName)
        editTextDescription = findViewById<AppCompatEditText>(R.id.textEditInputDescription)
        editTextProviderName = findViewById<AppCompatEditText>(R.id.textEditInputProviderName)
        editTextAmountValue = findViewById<AppCompatEditText>(R.id.textEditInputAmountValue)
        editButton = findViewById<AppCompatButton>(R.id.editButton)

        product = intent.getSerializableExtra("product") as? Product

        editTextName?.setText(product?.name)
        editTextDescription?.setText(product?.description)
        editTextProviderName?.setText(product?.providerName)
        editTextAmountValue?.setText(product?.amountValue.toString())

        editButton?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                editProduct()
            }
        })
    }

    private fun editProduct() {
        val snackBar = Snackbar.make(findViewById(android.R.id.content), "", Snackbar.LENGTH_LONG)
        snackBar.setBackgroundTint(Color.RED)
        snackBar.setAction("OK", View.OnClickListener { snackBar.dismiss() })
        if (editTextName?.getText().toString().trim { it <= ' ' } == "") {
            snackBar.setText("Nome é obrigatório!").show()
            editTextName?.requestFocus()
        } else if (editTextDescription?.getText().toString().trim { it <= ' ' } == "") {
            snackBar.setText("Descrição é obrigatório!").show()
            editTextDescription?.requestFocus()
        } else if (editTextProviderName?.getText().toString().trim { it <= ' ' } == "") {
            snackBar.setText("Nome do fornecedor é obrigatório!").show()
            editTextProviderName?.requestFocus()
        } else if (editTextAmountValue?.getText().toString().trim { it <= ' ' } == "") {
            snackBar.setText("Preço é obrigatório!").show()
            editTextAmountValue?.requestFocus()
        } else {
            product?.name = editTextName?.getText().toString().trim { it <= ' ' }
            product?.description = editTextDescription?.getText().toString().trim { it <= ' ' }
            product?.providerName = editTextProviderName?.getText().toString().trim { it <= ' ' }
            product?.amountValue = editTextAmountValue?.text.toString().toDouble()
            val productDAO: ProductDAO = AppDatabase.getInstance(applicationContext).productDAO()

            productDAO.update(product as Product)
            showMessage()
        }
    }

    fun showMessage() {
        val intent = Intent(applicationContext, ProductsListActivity::class.java)
        intent.putExtra("editSuccess", true)
        startActivity(intent)
    }
}