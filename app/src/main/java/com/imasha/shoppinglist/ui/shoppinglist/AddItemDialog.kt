package com.imasha.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.imasha.shoppinglist.R
import com.imasha.shoppinglist.data.db.entities.ShoppingItem

class AddItemDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.add_item_dialog)

        val tvAdd = findViewById<TextView>(R.id.tvAdd)
        val etName = findViewById<TextView>(R.id.etName)
        val etAmount = findViewById<EditText>(R.id.etAmount)
        val tvCancel = findViewById<TextView>(R.id.tvCancel)

        tvAdd?.setOnClickListener {
            val name = etName?.text.toString()
            val amount = etAmount?.text.toString()
            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter both name and amount.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel?.setOnClickListener {
            cancel()
        }
    }
}

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}