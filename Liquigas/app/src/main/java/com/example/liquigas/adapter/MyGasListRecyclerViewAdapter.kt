package com.example.liquigas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.liquigas.R
import com.example.liquigas.databinding.FragmentItemBinding
import com.example.liquigas.entity.Gas
import com.example.liquigas.entity.Item
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class MyGasListRecyclerViewAdapter(
    private val values: List<Gas>,
    private val cartItems: ArrayList<Item>,
    private val view: View,
) : RecyclerView.Adapter<MyGasListRecyclerViewAdapter.ViewHolder>() {
    val finishCartButton = view.findViewById<Button>(R.id.finishCartButton)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = "Botijão ${item.size}kg"
        Picasso.get().load(item.image).into(holder.image)

        holder.increaseQuantity.setOnClickListener {
            onIncreaseQuantity(holder)
        }

        holder.decreaseQuantity.setOnClickListener {
            onDecreaseQuantity(holder)
        }
    }

    override fun getItemCount(): Int = values.size

    fun onDecreaseQuantity(holder: ViewHolder) {
        val title = holder.title.text.toString()
        val quantity = holder.quantity.getText().toString().toInt()

        val decreaseValueQuantity = quantity - 1

        if (decreaseValueQuantity < 0) {
            holder.decreaseQuantity.isEnabled = false

            return
        }

        holder.quantity.setText((decreaseValueQuantity).toString())

        val findedCartItem = onFindCartItem(title)

        if (decreaseValueQuantity > 0) {
            val item = Item()
            item.product = title
            item.quantity = decreaseValueQuantity

            cartItems.set(cartItems.indexOf(findedCartItem), item)
        } else {
            cartItems.remove(findedCartItem)
        }

        setFinishCartButtonVisibility()

    }

    fun onIncreaseQuantity(holder: ViewHolder) {
        val title = holder.title.text.toString()
        val quantity = holder.quantity.getText().toString().toInt()

        val increaseValueQuantity = quantity + 1
        holder.quantity.setText((increaseValueQuantity).toString())

        val findedCartItem = onFindCartItem(title)

        if (findedCartItem == null) {
            val item = Item()
            item.product = title
            item.quantity = increaseValueQuantity

            cartItems.add(item)
        } else {
            val item = Item()
            item.product = title
            item.quantity = increaseValueQuantity

            cartItems.set(cartItems.indexOf(findedCartItem), item)
        }

        holder.decreaseQuantity.isEnabled = true
        setFinishCartButtonVisibility()
    }

    fun setFinishCartButtonVisibility() {
        finishCartButton.visibility = if (cartItems.isEmpty()) View.INVISIBLE else View.VISIBLE
    }

    fun onFindCartItem(title: String): Item? {
        val findedPreviousCartItem = cartItems.find { t -> t.product.equals(title) }

        return findedPreviousCartItem
    }

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.itemTextTitle
        val image: ImageView = binding.itemImageView
        val increaseQuantity = binding.increaseQuantityButton
        val decreaseQuantity = binding.decreaseQuantityButton
        val quantity = binding.editTextQuantity

        override fun toString(): String {
            return super.toString() + " '" + title.text + "'"
        }
    }
}