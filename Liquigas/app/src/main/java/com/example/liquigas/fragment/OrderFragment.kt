package com.example.liquigas.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.liquigas.R
import com.example.liquigas.adapter.MyOrderRecyclerViewAdapter
import com.example.liquigas.dao.AppDatabase
import com.example.liquigas.utils.LocalStorage


class OrderFragment : Fragment() {
    private var columnCount = 1
    var database: AppDatabase? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_list, container, false)

        database = AppDatabase.getInstance(view.context)

        val localStorage = LocalStorage(this.requireContext())

        val userId = localStorage.getValue(getString(R.string.user_logged))

        if (userId != null) {
            val orderList = database!!.orderDAO().getUserOrders(userId)

            if (view is RecyclerView) {
                with(view) {
                    layoutManager = when {
                        columnCount <= 1 -> LinearLayoutManager(context)
                        else -> GridLayoutManager(context, columnCount)
                    }
                    adapter = MyOrderRecyclerViewAdapter(orderList, database)
                }
            }
        }

        return view
    }
}