package com.example.organic.view

import android.content.Intent
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

        editTextName = findViewById<AppCompatEditText>(R.id.editTextName)
        editTextDescription = findViewById<AppCompatEditText>(R.id.editTextDescription)
        editTextProviderName = findViewById<AppCompatEditText>(R.id.editTextAmountValue)
        editTextAmountValue = findViewById<AppCompatEditText>(R.id.editTextProviderName)
        editButton = findViewById<AppCompatButton>(R.id.editProductButton)

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
        if (editTextName?.getText().toString().trim { it <= ' ' } == "") {
            Toast.makeText(applicationContext, "Nome é obrigatório!", Toast.LENGTH_LONG).show()
            editTextName?.requestFocus()
        } else if (editTextDescription?.getText().toString().trim { it <= ' ' } == "") {
            Toast.makeText(applicationContext, "Descrição é obrigatório!", Toast.LENGTH_LONG).show()
            editTextDescription?.requestFocus()
        } else if (editTextProviderName?.getText().toString().trim { it <= ' ' } == "") {
            Toast.makeText(applicationContext, "Nome do fornecedor é obrigatório!", Toast.LENGTH_LONG).show()
            editTextProviderName?.requestFocus()
        } else if (editTextAmountValue?.getText().toString().trim { it <= ' ' } == "") {
            Toast.makeText(applicationContext, "Preço é obrigatório!", Toast.LENGTH_LONG).show()
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
        val msg = "Registro alterado com sucesso! "
        //mostrando caixa de diálogo de sucesso
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.app_name)
        alertDialog.setMessage(msg)
        alertDialog.setPositiveButton("OK") { dialog, id -> // Forçando que o código retorne para a tela de consulta
            val intent = Intent(applicationContext, ProductsListActivity::class.java)
            startActivity(intent)
            finish()
        }
        alertDialog.show()
    }
}