package com.imasha.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imasha.shoppinglist.R
import com.imasha.shoppinglist.data.db.entities.ShoppingItem
import com.imasha.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]

        val tvName = holder.itemView.findViewById<TextView>(R.id.tvName)
        val tvAmount = holder.itemView.findViewById<TextView>(R.id.tvAmount)
        val ivDelete = holder.itemView.findViewById<ImageView>(R.id.ivDelete)
        val ivPlus = holder.itemView.findViewById<ImageView>(R.id.ivPlus)
        val ivMinus = holder.itemView.findViewById<ImageView>(R.id.ivMinus)

        tvName.text = curShoppingItem.name
        tvAmount.text = "${curShoppingItem.amount}"

        ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        ivPlus.setOnClickListener {
            curShoppingItem.amount++
            viewModel.insert(curShoppingItem)
        }

        ivMinus.setOnClickListener {
            if (curShoppingItem.amount > 1) {
                curShoppingItem.amount--
                viewModel.insert(curShoppingItem)
            }
        }
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}