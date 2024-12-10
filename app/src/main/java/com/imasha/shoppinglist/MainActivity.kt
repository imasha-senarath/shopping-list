package com.imasha.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.imasha.shoppinglist.data.db.ShoppingDatabase
import com.imasha.shoppinglist.data.repositories.ShoppingRepository
import com.imasha.shoppinglist.ui.shoppinglist.ShoppingViewModel
import com.imasha.shoppinglist.ui.shoppinglist.ShoppingViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = ShoppingDatabase(this);
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory)[ShoppingViewModel::class.java]
    }
}
