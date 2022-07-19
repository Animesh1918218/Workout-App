package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minuteworkout.databinding.ActivityBmiBinding

class BmiActivity : AppCompatActivity() {
    var myView:ActivityBmiBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myView = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(myView?.root)
        setSupportActionBar(myView?.myBmiToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        myView?.myBmiToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
      myView?.calculatebtn?.setOnClickListener {
          calculateBmi()
      }
    }
    private fun calculateBmi(){
        if(myView?.actualheighttxt?.text!!.isEmpty()||myView?.actualweighttxt?.text!!.isEmpty()){
            Toast.makeText(this, "Please Fill All Details", Toast.LENGTH_SHORT).show()
            return
        }
        val weight = myView?.actualweighttxt?.text.toString().toDouble()
        var height =myView?.actualheighttxt?.text.toString().toDouble()
        height = (height*height)/10000
        val bmi = weight/height
        myView?.bmiText?.text = String.format("Your BMI: %.2f",bmi)
        if(bmi.toDouble()<18.5){
            myView?.descriptionTxt?.text ="You Are UnderWeight"
            myView?.messageTxt?.text = "Eat Some Chips Buddy get Fat"
        }
        else if(bmi>18.5&&bmi<25)
        {
            myView?.descriptionTxt?.text ="You Are Healthy"
            myView?.messageTxt?.text = "Yeah Boiiiii You have the genes of becoming a superhero boiiiii"
        }
        else if(bmi>25&&bmi<30)
        {
            myView?.descriptionTxt?.text ="You Are OverWeight"
            myView?.messageTxt?.text = "Give Your FAt BOdy some Rest Will YA"
        }
        else {
            myView?.descriptionTxt?.text ="You Are Obese"
            myView?.messageTxt?.text = "Chill Out Continue Like that and you might outweigh a hippopotamus or actually you might already have"
        }
        myView?.bmiText?.visibility =View.VISIBLE
        myView?.descriptionTxt?.visibility =View.VISIBLE
        myView?.messageTxt?.visibility =View.VISIBLE

    }

    override fun onDestroy() {
        super.onDestroy()
        myView=null
    }

}