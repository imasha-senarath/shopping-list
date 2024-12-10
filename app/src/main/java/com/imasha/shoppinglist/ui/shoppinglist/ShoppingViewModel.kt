package com.imasha.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.imasha.shoppinglist.data.db.entities.ShoppingItem
import com.imasha.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {
    fun insert(item: ShoppingItem) =
        GlobalScope.launch {
            repository.insert(item)
        }

    fun delete(item: ShoppingItem) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllItems()
}