package com.example.a7minuteworkout

class AllExcersizes(
    private val id:Int,
    private val excersizeName:String,
    private val excerSizeImage:Int,
    private var isSelected:Boolean =false,
    private var isCompleted:Boolean =false
) {
    fun getId():Int{
        return id
    }
    fun getExcersizeName():String{
        return excersizeName
    }
    fun getExcersizeImage():Int{
        return excerSizeImage
    }
    fun setIsSelected(isSelected:Boolean){
        this.isSelected = isSelected
    }
    fun setIsCompleted(isCompleted:Boolean){
        this.isCompleted = isCompleted
    }
    fun getIsSelected():Boolean{
        return isSelected
    }
   fun getIsCompleted():Boolean{
       return isCompleted
   }
}