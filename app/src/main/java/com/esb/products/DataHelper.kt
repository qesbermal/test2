package com.esb.products


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

 class DataHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,
        DATABASE_VERSION
    ){
        companion object{
            private const val DATABASE_NAME = "products.db"
            private const val DATABASE_VERSION = 3
            const val TABLE_PRODUCT = "PRODUCT"
            const val KEY_PRODCODE = "prodCode"
            const val KEY_PRODNAME = "prodName"
            const val TABLE_UOM = "UOM"
            const val KEY_UOM = "uom"
            const val KEY_PRICE = "uomPrice"
        }

        override fun onCreate(db: SQLiteDatabase) {
            val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                    TABLE_PRODUCT + "(" +
                    "id " + " INTEGER PRIMARY KEY," +
                    "PRODCODE" + " TEXT, " +
                    "PRODNAME" + " TEXT)")
            db.execSQL(CREATE_PRODUCTS_TABLE)
            val CREATE_UOM_TABLE = ("CREATE TABLE "+
                    TABLE_UOM + "(" +
                    "productCode " + "TEXT REFERENCES " + TABLE_PRODUCT +
                    ", UOM" + "TEXT," + "PRICE" + "INTEGER"
                    )
            db.execSQL(CREATE_UOM_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCT")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_UOM")
            onCreate(db)
        }

        fun addProduct(products: Products):Boolean{
            var db = this.writableDatabase
            var values = ContentValues()
            values.put(KEY_PRODCODE,products.prodCode)
            values.put(KEY_PRODNAME,products.prodName)
            val success = db.insert(TABLE_PRODUCT,null,values)
//            db.close()
            if (success.toInt() == -1){
                Toast.makeText(context,"An error occurred in the insert, please try again", Toast.LENGTH_SHORT).show()
                return false
            }
            else{
                Toast.makeText(context,"Insert Complete, Please Refresh", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        fun deleteProduct(code: String){
            val db = this.writableDatabase
            db.delete(TABLE_PRODUCT, "$KEY_PRODCODE = ? ", arrayOf(code))
        }

        fun getAllProducts():ArrayList<Products>{
            val db = this.writableDatabase
            val productList : ArrayList<Products> = ArrayList()
            val selectAll = "SELECT * FROM $TABLE_PRODUCT"
            val cursor = db.rawQuery(selectAll,null)
            if (cursor.moveToFirst()){
                do{
                    val product = Products()
                    product.prodCode = cursor.getString(1)
                    product.prodName = cursor.getString(2)
                    productList.add(product)
                } while (cursor.moveToNext())
            }
            cursor.close()
            return productList
        }

     fun addUom(uom: Uom):Boolean{
         var db = this.writableDatabase
         var values = ContentValues()
         values.put(KEY_UOM,uom.uom)
         values.put(KEY_PRICE,uom.price)
         val success = db.insert(TABLE_PRODUCT,null,values)

         if (success.toInt() == -1){
             Toast.makeText(context,"An error occurred in the insert, please try again", Toast.LENGTH_SHORT).show()
             return false
         }
         else{
             Toast.makeText(context,"Insert Complete, Please Refresh", Toast.LENGTH_SHORT).show()
             return true
         }
     }

     fun deleteUom(uom: String){
         val db = this.writableDatabase
         db.delete(TABLE_UOM, "$KEY_PRODCODE = ? ", arrayOf(uom))
     }
     fun getAllUom():ArrayList<Uom>{
         val db = this.writableDatabase
         val uomList : ArrayList<Uom> = ArrayList()
         val selectAll = "SELECT * FROM $TABLE_UOM where $KEY_PRODCODE = ?"
         val cursor = db.rawQuery(selectAll,null)
         if (cursor.moveToFirst()){
             do{
                 val uom = Uom()
                 uom.uom = cursor.getString(1)
                 uom.price = cursor.getInt(2)
                 uomList.add(uom)
             } while (cursor.moveToNext())
         }
         cursor.close()
         return uomList
     }
}