package com.example.liquigas.fragment

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.liquigas.R
import com.example.liquigas.adapter.MyGasListRecyclerViewAdapter
import com.example.liquigas.dao.AppDatabase
import com.example.liquigas.entity.Gas
import com.example.liquigas.entity.Item
import com.example.liquigas.model.OrderViewModel


class GasListFragment : Fragment() {

    private var gasList = Gas.listGas()

    private var cartItems = arrayListOf<Item>()

    var database: AppDatabase? = null

    private var columnCount = 1

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.gasList)

        val finishCartButton = view.findViewById<Button>(R.id.finishCartButton)

        database = AppDatabase.getInstance(view.context)

        finishCartButton.setOnClickListener {
            sharedViewModel.setCartList(cartItems)

            Navigation.findNavController(view).navigate(R.id.action_nav_gas_list_to_nav_payment)
        }

        with(recyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

            adapter = MyGasListRecyclerViewAdapter(gasList, cartItems, view)
        }


        return view
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            GasListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}