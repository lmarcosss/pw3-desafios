package com.example.organic.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.organic.R
import com.example.organic.entity.Product
import com.example.organic.enum.MainActivityMenu
import com.google.android.material.snackbar.Snackbar
import kotlinx.serialization.json.JsonNull.content

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listaOpcoes = findViewById<ListView>(R.id.list_view)
        val itens = arrayOf("Cadastrar Produto", "Listagem de Produtos", "Sobre")
        val arrayItens = ArrayAdapter<String>(this, R.layout.list_item, R.id.text_view, itens)
        listaOpcoes.adapter = arrayItens
        listaOpcoes.onItemClickListener = this

        val registerSuccess = intent.getSerializableExtra("registerSuccess") as? Boolean

        if (registerSuccess != null) {
            val snackBar = Snackbar.make(findViewById(android.R.id.content), "Produto foi cadastrado com sucesso!", Snackbar.LENGTH_LONG)
            snackBar.setAction("OK", View.OnClickListener { snackBar.dismiss() })
            snackBar.show()
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p2) {
            MainActivityMenu.REGISTER.value -> {
                val intent = Intent(this, RegisterProductActivity::class.java)
                startActivity(intent)
            }
            MainActivityMenu.LIST.value -> {
                val intent = Intent(this, ProductsListActivity::class.java)
                startActivity(intent)
            }
            MainActivityMenu.ABOUT.value -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
    }
}