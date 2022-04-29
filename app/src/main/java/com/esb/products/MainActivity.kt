package com.esb.products

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.esb.products.databinding.ActivityMainBinding

//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.component_button_insert.*



class MainActivity : AppCompatActivity() {
    private var productListAdapter: ProductListAdapter = ProductListAdapter()
    private var uomListAdapter: UomListAdapter = UomListAdapter()
    private lateinit var viewModel: MainViewModel
//   private lateinit var newrecyclerview: RecyclerView
//    private lateinit var newArrayList: ArrayList<Products>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init recycleview and adapter
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        binding.rvProduct.adapter = productListAdapter

        //init observers
        viewModel.list.observe(this) {
            productListAdapter.submitList(it)
        }
//
//        binding.rvProduct.setRecyclerListener(){
//
//            setContentView(R.layout.activity_main2)
//        }

        //init button
        binding.addDataButton.setOnClickListener {
            val intent = Intent(this, InsertActivityProduct::class.java)
            startActivityForResult(intent, 1) // deprecated na to, diko pa kabisado ung bago, mas mabilis lng to
        }

        //setup listener
        productListAdapter.setupListener {
            val db = DataHelper(this)
            db.deleteProduct(it)
            viewModel.loadData()
        }
//        getUserData()
    }
//    private fun getUserData()
//    {
//        val adapter = ProductListAdapter()
//        newrecyclerview = findViewById(R.id.rv_product)
//        newrecyclerview.adapter = adapter
//        adapter.setOnItemClickListener(object : MainViewModel.onItemClickListener{
//            override fun onItemClick(position:Int){
//                val intent = Intent(this@MainActivity,MainActivity2::class.java)
//                startActivity(intent)
//            }
//        })
//
//    }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            viewModel.loadData()
        }
    }

        /**
         * OLD CODE
         *
         */
//        setContentView(R.layout.activity_main)

//        DataBindingUtil.setContentView<ItemProductBinding>(
//            this, R.layout.activity_main
//        ).apply {
//            this.lifecycleOwner = this@MainActivity
//            this.view = Products()
//        }
////        b_insert_product.setOnClickListener{
////            val intent = Intent(this, InsertActivity::class.java)
////            startActivity(intent)
////        }
//
//        newrecyclerView = findViewById(R.id.rv_product)
//        newrecyclerView.layoutManager = LinearLayoutManager(this)
//        newrecyclerView.setHasFixedSize(true)
//        val dataHelper = DataHelper(this)
//        Log.d("read","read  data")
//        val productList = dataHelper.getAllProducts()
//        val adapter = productListAdapter
//        newrecyclerView.adapter = adapter
//        adapter.submitList(newArrayList)
//        adapter.getUpdate()
////        rv_product.apply {
//////            setHasFixedSize(true)
////            layoutManager = LinearLayoutManager(this@MainActivity)
////            adapter = productAdapter
////        }
//
////        refresh_swipe.setOnRefreshListener {
////            refresh_swipe.isRefreshing=false
//          //  adapter.getUpdate()




