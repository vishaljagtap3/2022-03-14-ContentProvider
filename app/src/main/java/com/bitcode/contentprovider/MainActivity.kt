package com.bitcode.contentprovider

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var baseUri = Uri.parse("content://com.bitcode.ecomprovider")

        var productsUri = Uri.withAppendedPath(baseUri, "products")
        var productsUriWithRange = Uri.withAppendedPath(productsUri, "1100")
        productsUriWithRange = Uri.withAppendedPath(productsUriWithRange, "1350")

        var productsUriWithId = Uri.withAppendedPath(productsUri, "102")

        var customersUri = Uri.withAppendedPath(baseUri, "customers")

        var productsValues = ContentValues()
        productsValues.put("_id", 111)
        productsValues.put("title", "New Product1")
        productsValues.put("price", 14561)

        var newProductUri = contentResolver.insert(productsUri, productsValues)
        mt(newProductUri.toString())

        mt("----------------------------------")

        var c = contentResolver.query(
            newProductUri!!,
            null,
            null,
            null,
            null
        )

        /*var c = contentResolver.query(
            productsUri,
            null,
            "price > ? and price < ?",
            arrayOf("1100", "1350"),
            null
        )*/

        while (c!!.moveToNext()) {
            mt("${c.getInt(0)} - ${c.getString(1)} - ${c.getInt(2)}")
        }

        c.close()

        mt("--------------------------------------------")

        c = contentResolver.query(
            customersUri,
            null,
            null,
            null,
            null
        )
        while (c!!.moveToNext()) {
            mt("${c.getInt(0)} - ${c.getString(1)} - ${c.getString(2)}")
        }
        c.close()
    }

    private fun mt(text: String) {
        Log.e("tag", text)
    }
}