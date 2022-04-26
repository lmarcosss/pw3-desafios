package com.example.organic.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.organic.R
import com.example.organic.dao.AppDatabase
import com.example.organic.dao.ProductDAO
import com.example.organic.entity.Product
import com.example.organic.view.EditProductActivity
import com.example.organic.view.ProductsListActivity
import com.google.android.material.snackbar.Snackbar
import java.util.*

class QueryLineAdapter: BaseAdapter {
    private var layoutInflater: LayoutInflater? = null
    var products: MutableList<Product> = ArrayList()

    private var listProducts: ProductsListActivity? = null

    constructor(listarAtividades: ProductsListActivity?, products: MutableList<Product>) {
        this.products = products
        this.listProducts = listarAtividades
        layoutInflater = this.listProducts?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return products.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val viewActivityLine: View = layoutInflater!!.inflate(R.layout.activity_line, null)

        val textViewCod: AppCompatTextView = viewActivityLine.findViewById(R.id.textViewCodigo)
        val textViewName: AppCompatTextView = viewActivityLine.findViewById(R.id.textViewNome)
        val textViewDescription: AppCompatTextView = viewActivityLine.findViewById(R.id.textViewDescricao)
        val textViewAmountValue: AppCompatTextView = viewActivityLine.findViewById(R.id.textViewAmountValue)
        val textViewProviderName: AppCompatTextView = viewActivityLine.findViewById(R.id.textViewProviderName)
        val deleteButton: ImageButton = viewActivityLine.findViewById(R.id.deleteButton)
        val editButton: ImageButton = viewActivityLine.findViewById(R.id.editButton)

        val product: Product = products.get(position)
        textViewCod.setText(product.id.toString())
        textViewName.setText(product.name.toString())
        textViewDescription.setText("Descrição: ${product.description.toString()}")
        textViewAmountValue.setText("Valor: R$${product.amountValue.toString()}")
        textViewProviderName.setText("Fornecedor: ${product.providerName.toString()}")


        deleteButton.setOnClickListener { view ->
            val productDAO: ProductDAO? = AppDatabase.getInstance(listProducts!!.applicationContext)?.productDAO()
            val products: List<Product> = productDAO!!.getAllProducts()
            val product = (products[position]) as Product
            productDAO?.delete(product)
            updateList(position)

            val mensagem = "Produto excluído com sucesso!"
            val snackBar = Snackbar.make(view, mensagem, Snackbar.LENGTH_LONG)
            snackBar.setAction("OK", View.OnClickListener { snackBar.dismiss() })
            snackBar.show()
        }

        editButton.setOnClickListener {
            val intent = Intent(listProducts, EditProductActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            val product = products[position]
            intent.putExtra("product", product)
            listProducts?.startActivity(intent)
        }

        return viewActivityLine
    }

    fun updateList(position: Int) {
        this.products.removeAt(position)
        notifyDataSetChanged()
    }
}