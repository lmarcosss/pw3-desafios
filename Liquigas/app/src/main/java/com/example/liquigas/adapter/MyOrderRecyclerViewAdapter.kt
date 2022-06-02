package com.example.liquigas.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.liquigas.dao.AppDatabase
import com.example.liquigas.databinding.FragmentOrderBinding
import com.example.liquigas.entity.Order
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MyOrderRecyclerViewAdapter(
    private val values: List<Order>,
    private val database: AppDatabase?,
) : RecyclerView.Adapter<MyOrderRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        val localDateTime: LocalDateTime = LocalDateTime.parse(item.created_at)
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        val dateFormatted: String = formatter.format(localDateTime)
        val orderItems = database!!.itemDAO().getOrderWithItemByOrder(item.id.toString())
        val orderItemsText = orderItems.joinToString("\n") { t -> "${t.product} - ${t.quantity} Unidades" }

        holder.orderItemTitle.text = orderItemsText
        holder.orderItemPaymentType.text = " ${item.paymentType}"
        holder.orderItemDate.text = " ${dateFormatted}"
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        val orderItemTitle: TextView = binding.orderItemTitle
        val orderItemPaymentType: TextView = binding.orderItemPaymentType
        val orderItemDate: TextView = binding.orderItemDate

        override fun toString(): String {
            return super.toString() + " '" + orderItemPaymentType.text + "'"
        }
    }

}