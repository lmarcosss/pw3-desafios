package br.edu.ifrs.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    var textInputEmail: TextInputEditText? = null
    var textInputPassword: TextInputEditText? = null
    var loginButton: AppCompatButton? = null
    var textInputLayoutEmail: TextInputLayout? = null
    var textInputLayoutPassword: TextInputLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.loginButton)
        textInputEmail = findViewById(R.id.textInputEmail)
        textInputPassword = findViewById(R.id.textInputPassword)
        textInputLayoutEmail = findViewById(R.id.textInputlayoutEmail)
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword)

        textInputPassword?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onDone(null)
                return@setOnEditorActionListener true
            }
            false
        }

        loginButton?.setOnClickListener { view -> onDone(view) }
    }

    private fun onDone(view: View?) {
        var isValidFields = validarCampos()
        if (isValidFields) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else if (view != null) {
            val snackBar = Snackbar.make(view, "Problemas ao fazer login", Snackbar.LENGTH_LONG)

            snackBar.show()
        }
    }
    private fun validarCampos(): Boolean {
        if (textInputEmail?.text.toString().isEmpty()) {
            textInputLayoutEmail?.isErrorEnabled = true
            textInputLayoutEmail?.error = "Informe o seu e-mail"
            return false
        } else {
            textInputLayoutEmail?.isErrorEnabled = false
        }
        if (textInputPassword?.text.toString().isEmpty()) {
            textInputLayoutPassword?.isErrorEnabled = true
            textInputLayoutPassword?.error = "Informe a sua senha"
            return false
        } else {
            textInputLayoutPassword?.isErrorEnabled = false
        }
        Log.d("validacao", "saiu no validar")
        return true
    }
}