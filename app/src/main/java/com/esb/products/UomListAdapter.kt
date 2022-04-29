package com.esb.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.esb.products.databinding.ItemUomBinding

class UomListAdapter() : ListAdapter<Uom, UomListAdapter.MyViewHolder>(DIFF_CALLBACK) {
    var deleteListener: ((uom: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUomBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.view = getItem(position)
        holder.binding.uomDeleteButton.setOnClickListener {
            deleteListener?.let {
                it(getItem(position).uom)
            }
        }
    }

    fun setupListener(_ls: ((productCode: String) -> Unit)){
        deleteListener = _ls
    }

    class MyViewHolder(val binding: ItemUomBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<Uom> =
            object : DiffUtil.ItemCallback<Uom>() {
                override fun areItemsTheSame(old: Uom, new: Uom): Boolean {
                    return old.uom == new.uom && old.price == new.price
                }

                override fun areContentsTheSame(old: Uom, new: Uom): Boolean {
                    return old == new
                }
            }
    }
}