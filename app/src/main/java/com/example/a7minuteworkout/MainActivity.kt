package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minuteworkout.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
     private var viewBinding:ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)
         viewBinding?.startbtn?.setOnClickListener {
            val intent =Intent(this,ExcerciseActivity::class.java)
             startActivity(intent)
         }
        viewBinding?.bmibtn?.setOnClickListener {
            val intent =Intent(this,BmiActivity::class.java)
            startActivity(intent)
        }
        viewBinding?.historybtn?.setOnClickListener {
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }

    }

}