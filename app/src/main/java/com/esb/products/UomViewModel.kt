package com.esb.products

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class UomViewModel(application: Application): AndroidViewModel(application) {
    val db = DataHelper(application)
    var list = MutableLiveData<ArrayList<Uom>>()

    init {
        loadData()
    }

    fun loadData() {
        list.postValue(db.getAllUom())//recommended
//        list.value = db.getAllProducts()
    }
}