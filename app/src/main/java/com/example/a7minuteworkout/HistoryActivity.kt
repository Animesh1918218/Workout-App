package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var myView:ActivityHistoryBinding?=null
    private var myDao:HistoryDao?=null
    private var myHistoryRecyclerViewAdapter:HistoryAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myView = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(myView?.root)
        setSupportActionBar(myView?.myHistoryToolar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        myView?.myHistoryToolar?.setNavigationOnClickListener {
            onBackPressed()
        }
        myDao = (application as HistorDatabaseApplication).db.getDao()
        getallHistoryContent()
        myView?.DeletBtn?.setOnClickListener {
            deleteAllContent()
        }
    }
    fun getallHistoryContent(){
        lifecycleScope.launch {
            myDao?.getAllItems()?.collect {
               val myData = ArrayList(it)
                if(myData.isNotEmpty()){
                myHistoryRecyclerViewAdapter = HistoryAdapter(myData)
                myView?.myHistoryrv?.layoutManager =LinearLayoutManager(this@HistoryActivity,LinearLayoutManager.VERTICAL,false)
                myView?.myHistoryrv?.adapter =myHistoryRecyclerViewAdapter
                    myView?.myHistoryrv?.visibility =View.VISIBLE
                    myView?.DeletBtn?.visibility = View.VISIBLE
                    myView?.norecordfoundtxt?.visibility =View.GONE

                }
                else{
                    myView?.myHistoryrv?.visibility =View.GONE
                    myView?.DeletBtn?.visibility = View.GONE
                    myView?.norecordfoundtxt?.visibility =View.VISIBLE


                }

            }
        }
    }
private fun deleteAllContent(){
    lifecycleScope.launch {
        myDao?.getAllItems()?.collect {
            allitems->
            for(i in allitems){
                myDao?.deleteitem(i)
            }
            Toast.makeText(baseContext, "All Items Delted Successfully", Toast.LENGTH_SHORT).show()
        }
        myView?.DeletBtn?.visibility =View.GONE
    }
}
}