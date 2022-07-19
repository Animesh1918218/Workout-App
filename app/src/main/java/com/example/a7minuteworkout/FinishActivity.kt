package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.a7minuteworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
   private var myView:ActivityFinishBinding?=null
           private var myDao:HistoryDao?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myView = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(myView?.root)
        myDao = (application as HistorDatabaseApplication).db.getDao()
        addRecord()
        myView?.mybtn?.setOnClickListener {
            finish()
        }
    }

    fun addRecord(){
        val mdate=Calendar.getInstance()
        val sdf =SimpleDateFormat("dd MMM yyyy HH:MM:SS",Locale.getDefault())
        val time = sdf.format(mdate.time)
        lifecycleScope.launch {
            myDao?.InsertItem(HistoryData(date = time))
        }
        Toast.makeText(this, "Time Succesfully Stored", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        myView =null
    }
}