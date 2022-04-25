package com.example.organic.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.organic.R

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listaOpcoes = findViewById<ListView>(R.id.list_view)
        val itens = arrayOf("Cadastrar Produto", "Listagem de Produtos", "Sobre")
        val arrayItens = ArrayAdapter<String>(this, R.layout.list_item, R.id.text_view, itens)
        listaOpcoes.adapter = arrayItens
        listaOpcoes.onItemClickListener = this
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p2 == 0) {
            val intent = Intent(this, RegisterProductActivity::class.java)
            startActivity(intent)
        }

        if (p2 == 1) {
            val intent = Intent(this, ProductsListActivity::class.java)
            startActivity(intent)
        }

        if (p2 == 2) {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}