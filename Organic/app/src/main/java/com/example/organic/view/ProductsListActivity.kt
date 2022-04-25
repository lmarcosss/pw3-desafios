package com.example.organic.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.organic.R
import com.example.organic.adapter.QueryLineAdapter
import com.example.organic.dao.AppDatabase
import com.example.organic.entity.Product

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
    }

    protected fun getAll(products: MutableList<Product>) {
        productsList?.setAdapter(QueryLineAdapter(this, products))
    }
}