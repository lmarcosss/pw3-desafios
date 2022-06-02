package com.example.liquigas.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.liquigas.entity.Item

class OrderViewModel: ViewModel() {
    private val _cartList = MutableLiveData<ArrayList<Item>>()
    val cartList: LiveData<ArrayList<Item>> = _cartList

    fun setCartList(cartList: ArrayList<Item>) {
        _cartList.value = cartList
    }
}