package com.example.a7minuteworkout

object AllExcersizeList {
    fun getAllExcersizes():ArrayList<AllExcersizes>{
        val allExcersizes = ArrayList<AllExcersizes>()
        allExcersizes.add(AllExcersizes(1,"Lunges",R.drawable.lunges))
        allExcersizes.add(AllExcersizes(2,"PushUps",R.drawable.pushups))
        allExcersizes.add(AllExcersizes(3,"Squats",R.drawable.squats))
        allExcersizes.add(AllExcersizes(4,"Burpees",R.drawable.bupees))
        allExcersizes.add(AllExcersizes(5,"Plank",R.drawable.planks))
        return allExcersizes
    }
}