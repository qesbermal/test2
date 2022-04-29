package com.esb.products

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esb.products.databinding.ActivityInsertUomBinding

class InsertActivityUom : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityInsertUomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DataHelper(this)

        binding.uomInsertButton.setOnClickListener {
            //add confirmation dialog

            db.addProduct(Products(
                binding.uomEt.text.toString(),
                binding.priceEt.text.toString()
            ))

            setResult(1)
            finish()
        }
    }
}