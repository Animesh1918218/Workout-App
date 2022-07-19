package com.example.a7minuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.databinding.ActivityExcerciseBinding
import com.example.a7minuteworkout.databinding.DialogAlertLayoutBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList

class ExcerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var myView: ActivityExcerciseBinding? = null
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var exercizeTimer:CountDownTimer?=null
    private var excersizeProgress =0
    private var myAllExcersize:ArrayList<AllExcersizes>?=null
    private var displayedExcersize =0



    private val totalRestTime:Long =10
    private val totalExcersizeTime:Long=30



    private var textToSpeech:TextToSpeech?=null
    private var mediaPlayer:MediaPlayer?=null
   private var excersizeadapter:ExcersizeItemAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myView = ActivityExcerciseBinding.inflate(layoutInflater)
        setContentView(myView?.root)
        setSupportActionBar(myView?.myToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        myView?.myToolBar?.setNavigationOnClickListener {
           setUpCustomDialog()
        }
        myAllExcersize =AllExcersizeList.getAllExcersizes()
        textToSpeech = TextToSpeech(this,this)
        setUpMyAdpater()
        setMyTimer()
    }

    private fun setMyTimer() {
        myView?.timepg?.progress =restProgress
        setMainFrameVisibility()

        restTimer =object :CountDownTimer(totalRestTime*1000,1000){
            override fun onTick(millisUntilFinished: Long){
                restProgress++

                myView?.timertext?.text = (myView?.timepg!!.max - restProgress).toString()
                myView?.timepg?.progress =restProgress

            }

            override fun onFinish() {
                Toast.makeText(this@ExcerciseActivity,"Timer Started",Toast.LENGTH_SHORT).show()
                setExeciseTimer()
            }
        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){

            restTimer?.cancel()
            restProgress=0
        }
        if(exercizeTimer!=null){
        exercizeTimer?.cancel()
        excersizeProgress=0
        }
       if(textToSpeech!=null){
           textToSpeech?.stop()
           textToSpeech?.shutdown()
       }
        if(mediaPlayer!=null){
            mediaPlayer?.stop()
            mediaPlayer?.release()
        }
        myView =null
    }

    private fun setExeciseTimer(){
        myView?.exercise1pg?.progress = excersizeProgress
        setExcersizeFrameVisisbility()
        mediaPlayer =MediaPlayer.create(this,R.raw.workout_sound)
        mediaPlayer?.start()
        myAllExcersize!![displayedExcersize].setIsSelected(true)
        excersizeadapter?.notifyItemChanged(displayedExcersize)
        exercizeTimer =object : CountDownTimer(totalExcersizeTime*1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                excersizeProgress++
                myView?.excersize1text?.text = (myView?.exercise1pg!!.max - excersizeProgress).toString()
                if(myView?.excersize1text?.text.toString()=="15"){
                    setTextToSpeech("15 Seconds Remaining")
                }
                else if((myView?.exercise1pg!!.max - excersizeProgress)<=10){
                    setTextToSpeech(myView?.excersize1text?.text.toString())
                }
                myView?.exercise1pg?.progress = excersizeProgress
            }

            override fun onFinish() {
                myAllExcersize!![displayedExcersize].setIsSelected(false)
                myAllExcersize!![displayedExcersize].setIsCompleted(true)
                excersizeadapter?.notifyItemChanged(displayedExcersize)
                displayedExcersize++
                mediaPlayer?.stop()

                if(displayedExcersize==myAllExcersize?.size){
                    Toast.makeText(baseContext, "Excersize Completed", Toast.LENGTH_SHORT).show()
                    finish()
                    val intent =Intent(baseContext,FinishActivity::class.java)
                    startActivity(intent)


                }
                else{
                setMyTimer()
                }
            }
        }.start()
    }


    private fun setUpMyAdpater(){
       myView?.rvExcerssize?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
       excersizeadapter = ExcersizeItemAdapter(myAllExcersize!!)
        myView?.rvExcerssize?.adapter = excersizeadapter
    }
    private fun setExcersizeFrameVisisbility(){
        myView?.floriginal?.visibility = FrameLayout.GONE
        myView?.mymaintext?.visibility = View.GONE
        myView?.flexcersize1?.visibility =FrameLayout.VISIBLE
        myView?.NextExcersizeName?.visibility =View.GONE
        myView?.nextexcersizetext?.visibility=View.GONE
        myView?.mainImageview?.visibility =View.VISIBLE
        myView?.exsersizeTimerStartedText?.visibility = View.VISIBLE
        myView?.heading?.visibility =View.VISIBLE
        if(exercizeTimer!=null){
            exercizeTimer?.cancel()
            excersizeProgress=0
        }
        myView?.heading?.text = "${myAllExcersize!![displayedExcersize].getExcersizeName()}"
        myView?.mainImageview?.setImageResource(myAllExcersize!![displayedExcersize].getExcersizeImage())
    }


    private fun setMainFrameVisibility(){
        myView?.floriginal?.visibility = FrameLayout.VISIBLE
        myView?.mymaintext?.visibility = View.VISIBLE
        myView?.flexcersize1?.visibility =FrameLayout.GONE
        myView?.NextExcersizeName?.visibility =View.VISIBLE
        myView?.nextexcersizetext?.visibility=View.VISIBLE
        myView?.mainImageview?.visibility =View.GONE
        myView?.exsersizeTimerStartedText?.visibility = View.GONE
        myView?.heading?.visibility =View.GONE
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        myView?.NextExcersizeName?.text =myAllExcersize!![displayedExcersize].getExcersizeName()
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val result = textToSpeech?.setLanguage(Locale.US)
            if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("Error","Language Not Supported")
            }
        }
    }


    private fun setTextToSpeech(number:String){
        if(number.isNotEmpty()){
            textToSpeech?.speak(number,TextToSpeech.QUEUE_FLUSH,null,"")
        }
    }

   private fun setUpCustomDialog(){
       val dialog =Dialog(this)

       val dialogView = DialogAlertLayoutBinding.inflate(layoutInflater)
       dialog.setContentView(dialogView.root)
       dialogView.yes.setOnClickListener {
           finish()
           dialog.dismiss()
       }
       dialogView.no.setOnClickListener {
           dialog.dismiss()
       }
       dialog.show()
   }
}
