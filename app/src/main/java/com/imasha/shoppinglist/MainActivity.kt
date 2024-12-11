package com.imasha.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.imasha.shoppinglist.adapter.ShoppingItemAdapter
import com.imasha.shoppinglist.data.db.ShoppingDatabase
import com.imasha.shoppinglist.data.db.entities.ShoppingItem
import com.imasha.shoppinglist.data.repositories.ShoppingRepository
import com.imasha.shoppinglist.ui.shoppinglist.AddDialogListener
import com.imasha.shoppinglist.ui.shoppinglist.AddItemDialog
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

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        val rvShoppingItems = findViewById<RecyclerView>(R.id.rvShoppingItems)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            AddItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.insert(item)
                    }
                }).show()
        }
    }
}
