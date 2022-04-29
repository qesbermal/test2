package com.esb.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.esb.products.databinding.ItemProductBinding

class ProductListAdapter : ListAdapter<Products, ProductListAdapter.MyViewHolder>(DIFF_CALLBACK) {
    var deleteListener: ((productCode: String) -> Unit)? = null
//        private lateinit var mListener : onItemClickListener
//
//        interface onItemClickListener {
//            fun onItemClick(position: Int)
//        }
//        fun setOnItemClickListener(listener: onItemClickListener) {
//            mListener = listener
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val binding = ActivityInsertBinding.inflate(LayoutInflater.from(parent.context))
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.binding.prod = getItem(position)
        holder.binding.cv.setOnClickListener(){
            getItem(position)

        }
        holder.binding.view = getItem(position)
        holder.binding.productDeleteButton.setOnClickListener {
            deleteListener?.let {
                it(getItem(position).prodCode)
            }
        }
    }
    fun setupListener(_ls: ((productCode: String) -> Unit)){
        deleteListener = _ls
    }
        class MyViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)
        companion object {

            var DIFF_CALLBACK: DiffUtil.ItemCallback<Products> =
                object : DiffUtil.ItemCallback<Products>() {
                    override fun areItemsTheSame(old: Products, new: Products): Boolean {
                        return old.prodName == new.prodName && old.prodCode == new.prodCode
                    }

                    override fun areContentsTheSame(old: Products, new: Products): Boolean {
                        return old == new
                    }
                }
        }

        }
//    override fun getItemCount(): Int {
//        if(itemCount==0){
//            Toast.makeText(context,"List is empty", Toast.LENGTH_LONG).show()
//        }else{
//
//        }
//        return listAdapter.itemCount
//    }

//    class MyViewHolder(val binding: ActivityInsertBinding) : RecyclerView.ViewHolder(binding.root)


//    fun onDelete(){
//        val alertDialogBuilder = AlertDialog.Builder(context)
//        alertDialogBuilder.setTitle("Confirm")
//            .setMessage("Delete this data?")
//            .setCancelable(true)
//            .setPositiveButton("No"){dialog,which->
//                Toast.makeText(context,"Delete cancelled", Toast.LENGTH_SHORT).show()
//            }
//            .setNegativeButton("Yes"){dialog, which ->
//                val db = DataHelper(context)
//                db.deleteProduct(products = Products())
//                notifyDataSetChanged()
//                Toast.makeText(context,"Delete success", Toast.LENGTH_SHORT).show()
//            }
//        val alertDialog = alertDialogBuilder.create()
//        alertDialog.show()
//    }
//
//    fun getUpdate() {
//        val db = DataHelper(context)
//        db.getAllProducts()
//        notifyDataSetChanged()
//    }
