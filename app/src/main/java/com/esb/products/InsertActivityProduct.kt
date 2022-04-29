package com.esb.products

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esb.products.databinding.ActivityInsertProductBinding

class InsertActivityProduct : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_insert_product)

        val binding = ActivityInsertProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DataHelper(this)

        binding.productInsertButton.setOnClickListener {
            //add confirmation dialog

            db.addProduct(Products(
                binding.etProdCode.text.toString(),
                binding.etName.text.toString()
            ))

            setResult(1)
            finish()
        }
    }
}


//        val dataHelper = DataHelper(this)
//        onInsertButton(){
//            val alertDialogBuilder = AlertDialog.Builder(this)
//            alertDialogBuilder.setTitle("Confirm")
//                .setMessage("Are you sure to insert it?")
//                .setCancelable(true)
//                .setPositiveButton("No"){dialog,which->
//                }
//                .setNegativeButton("Yes"){dialog,which->
//                    val prodCode = Integer.parseInt(et_prodCode.text.toString())
//                    val prodName = et_name.text.toString()
//                    val prodPrice = et_price.text.toString()
//                   dataHelper.addProduct(Products(prodCode,prodName,prodPrice.indices.toString()))
//                    et_name.setText("")
//                    et_prodCode.setText("")
//                    et_price.setText("")
//                    finish()
//                }
//            val alertDialog = alertDialogBuilder.create()
//            alertDialog.show()
//        }
