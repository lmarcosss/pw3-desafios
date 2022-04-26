package com.example.organic.view

import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.organic.R
import com.example.organic.adapter.QueryLineAdapter
import com.example.organic.dao.AppDatabase
import com.example.organic.entity.Product
import com.google.android.material.snackbar.Snackbar

class ProductsListActivity : AppCompatActivity() {
    private var productsList: ListView? = null
    var database: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val actionBar = supportActionBar

        database = AppDatabase.getInstance(this)

        actionBar!!.title = "Listagem de produtos"
        actionBar.setDisplayHomeAsUpEnabled(true)

        productsList = findViewById<ListView>(R.id.listViewProducts)

        val products = database?.productDAO()!!.getAllProducts()

        getAll(products)

        val editSuccess = intent.getSerializableExtra("editSuccess") as? Boolean

        if (editSuccess != null) {
            val snackBar = Snackbar.make(findViewById(android.R.id.content), "Produto foi editado com sucesso!", Snackbar.LENGTH_LONG)
            snackBar.setAction("OK", View.OnClickListener { snackBar.dismiss() })
            snackBar.show()
        }
    }

    protected fun getAll(products: MutableList<Product>) {
        productsList?.setAdapter(QueryLineAdapter(this, products))
    }
}