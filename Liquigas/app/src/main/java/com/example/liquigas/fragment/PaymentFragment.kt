package com.example.liquigas.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.liquigas.R
import com.example.liquigas.dao.AppDatabase
import com.example.liquigas.entity.Item
import com.example.liquigas.entity.Order
import com.example.liquigas.model.OrderViewModel
import com.example.liquigas.utils.LocalStorage
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDateTime

class PaymentFragment : Fragment() {
    private val sharedViewModel: OrderViewModel by activityViewModels()

    var database: AppDatabase? = null

    var checkedValue: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_payment, container, false)

        val radioGroupPaymentType = view.findViewById<RadioGroup>(R.id.paymentTypeRadioGroup)
        val finishPaymentButton = view.findViewById<Button>(R.id.finishPaymentButton)

        database = AppDatabase.getInstance(view.context)

        radioGroupPaymentType.setOnCheckedChangeListener { _, i ->
            var radioButton = view.findViewById<RadioButton>(i)

            checkedValue = radioButton.text.toString()

            if (radioButton != null) {
                finishPaymentButton.isEnabled = true
            }
        }

        finishPaymentButton.setOnClickListener {
            val localStorage = LocalStorage(this.requireContext())

            val userId = localStorage.getValue(getString(R.string.user_logged))

            val cartList = sharedViewModel.cartList

            if (userId != null) {
                val order = Order()

                order.userId = userId
                order.paymentType = checkedValue
                order.created_at = LocalDateTime.now().toString()

                val orderId = database!!.orderDAO().insert(order)

                cartList.value!!.stream().forEach { t ->
                    val item = Item()

                    item.orderId = orderId.toInt()
                    item.product = t.product
                    item.quantity = t.quantity

                    database!!.itemDAO().insert(item)
                }

                Navigation.findNavController(view).navigate(R.id.nav_home)

                val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)

                snackBar.setAction("OK", View.OnClickListener { snackBar.dismiss() })

                snackBar.setText("Oba, logo em seguida entregaremos o pedido no endereço cadastrado!").show()
            }
        }

        return view
    }
}