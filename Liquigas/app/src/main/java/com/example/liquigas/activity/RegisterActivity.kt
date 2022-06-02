
package com.example.liquigas.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.liquigas.R
import com.example.liquigas.dao.AppDatabase
import com.example.liquigas.entity.User
import com.example.liquigas.utils.LocalStorage
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    private var database: AppDatabase? = null
    private var nameLayout: TextInputLayout? = null
    private var addressLayout: TextInputLayout? = null
    private var nameInput: TextInputEditText? = null
    private var addressInput: TextInputEditText? = null
    private var loginButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        database = AppDatabase.getInstance(this)
        loginButton = findViewById(R.id.loginButton)
        nameLayout = findViewById(R.id.nameLayout)
        addressLayout = findViewById(R.id.addressLayout)
        nameInput = findViewById(R.id.nameInput)
        addressInput = findViewById(R.id.addressInput)

        loginButton?.setOnClickListener { view -> onDone(view) }
    }

    private fun onDone(view: View?) {
        var isValidFields = validateFields()

        if (isValidFields) {
            val intent = Intent(this, MainActivity::class.java)

            var name = nameInput?.text.toString()
            val address = addressInput?.text.toString()

            val user = User()
            user.name = name
            user.address = address


            val databaseUserDAO = database!!.userDAO()
            val userId = databaseUserDAO.insert(user).toString()

            val localStorage = LocalStorage(this)

            localStorage.setValue(getString(R.string.user_logged), userId)

            startActivity(intent)
        } else if (view != null) {
            val snackBar = Snackbar.make(view, "Problemas ao fazer login", Snackbar.LENGTH_LONG)

            snackBar.show()
        }
    }

    private fun validateFields(): Boolean {
        if (nameInput?.text.toString().isEmpty()) {
            nameLayout?.isErrorEnabled = true
            nameLayout?.error = "Informe o seu nome"
            return false
        } else {
            nameLayout?.isErrorEnabled = false
        }
        if (addressInput?.text.toString().isEmpty()) {
            addressLayout?.isErrorEnabled = true
            addressLayout?.error = "Informe a seu endereço"
            return false
        } else {
            addressLayout?.isErrorEnabled = false
        }

        return true
    }
}