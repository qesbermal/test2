package com.esb.products


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

//import com.diana.products.MainViewModel.onItemClickListener

abstract class MainViewModel(application: Application): AndroidViewModel(application) {
    val db = DataHelper(application)
    val list = MutableLiveData<ArrayList<Products>>()
//    private lateinit var mListener : onItemClickListener

//    interface onItemClickListener{
//         fun onItemClick(position:Int)
//    }
//    fun setOnItemClickListener(listener: onItemClickListener){
//        mListener = listener
//    }

    init {
        loadData()
    }

    fun loadData() {
        list.postValue(db.getAllProducts())//recommended
//        list.value = db.getAllProducts()
    }


}

//    var newlist = arrayListOf<Products>()
//    val prodNameEt = MutableLiveData<String>()
//    val prodCodeEt = MutableLiveData<Int>()
//    val prodPriceEt = MutableLiveData<String>()
//    val prodNameTv = MutableLiveData<String>()
//    val prodCodeTv = MutableLiveData<Int>()
//    val prodPriceTv = MutableLiveData<String>()

//    fun add(products: Products){
//        newlist.add(products)
//        list.value=newlist
//    }
//
//    fun remove(products: Products){
//        newlist.remove(products)
//        list.value=newlist
//    }

//    fun onInsertButton(context: Context)
//    {
//        val dataHelper = DataHelper(context)
//        val alertDialogBuilder = AlertDialog.Builder(context)
//
//        alertDialogBuilder.setTitle("Confirm")
//            .setMessage("Are you sure to insert it?")
//            .setCancelable(true)
//            .setPositiveButton("No"){dialog,which->
//            }
//            .setNegativeButton("Yes"){dialog,which->
//                prodNameTv.value = prodNameEt.value
//                prodCodeTv.value = prodCodeEt.value
//                prodPriceTv.value = prodPriceEt.value
//                val prodCode = Integer.parseInt(prodCodeTv.toString())
//                    val prodName = prodNameEt.toString()
//                    val prodPrice = prodPriceEt.toString()
//                dataHelper.addProduct(Products(prodCode,prodName,prodPrice.indices.toString()))
////                prodNameEt.set("")
////                et_prodCode.setText("")
////                et_price.setText("")
////                finish()
//            }
//        val alertDialog = alertDialogBuilder.create()
//        alertDialog.show()
//    }
