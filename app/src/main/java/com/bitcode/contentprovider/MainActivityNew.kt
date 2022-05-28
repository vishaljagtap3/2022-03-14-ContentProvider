package com.bitcode.contentprovider

import android.net.Uri
import android.os.Bundle
import android.provider.CallLog
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivityNew : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //readSettings()
        //readCallLogs()
        readSMS()
    }

    private fun readSMS() {
        var uri = Uri.parse("content://sms")
        var c = contentResolver.query(uri, null, null, null, null)!!

        for(colName in c.columnNames) {
            mt(colName)
        }

        val colId = c.getColumnIndex("_id")
        val colType = c.getColumnIndex("type")
        val colBody = c.getColumnIndex("body")
        val colAddress = c.getColumnIndex("address")
        val colDate = c.getColumnIndex("date")
        val colSeen = c.getColumnIndex("seen")



        while (c.moveToNext()) {
            mt("${c.getInt(colId)} -- ${c.getInt(colType)} -- ${c.getString(colBody)} -- ${c.getString(colAddress)} -- ${c.getString(colDate)} -- ${c.getInt(colSeen)}")
        }

        c.close()
    }

    private fun readCallLogs() {
        var uri = android.provider.CallLog.Calls.CONTENT_URI
        var c = contentResolver.query(uri, null, null, null, null)!!

        for(colName in c.columnNames) {
            mt(colName)
        }
        mt("****************************")

        val colIndexName = c.getColumnIndex(CallLog.Calls.CACHED_NAME)
        val colIndexType = c.getColumnIndex(CallLog.Calls.TYPE)
        val colIndexNumber = c.getColumnIndex(CallLog.Calls.NUMBER)
        val colIndexDateTime = c.getColumnIndex(CallLog.Calls.DATE)


        while(c.moveToNext()) {
            mt("${c.getString(colIndexName)} -- ${c.getString(colIndexNumber)} -- ${c.getInt(colIndexType)} -- ${c.getString(colIndexDateTime)}")
        }
        c.close()
    }

    private fun readSettings() {
        var uri = android.provider.Settings.System.CONTENT_URI
        var c =
        contentResolver.query(uri, null, null, null, null)!!

        var nameColIndex = c.getColumnIndex(Settings.System.NAME)
        var valColIndex = c.getColumnIndex(Settings.System.VALUE)

        while(c.moveToNext()) {
            mt("${c.getString(nameColIndex)}")
            mt("${c.getString(valColIndex)}")
            mt("-------------------------------")
        }
        c.close()
    }

    private fun mt(text : String) {
        Log.e("tag", text)
    }
}