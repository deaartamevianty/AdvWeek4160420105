package com.example.advweek4160420105.model

import com.google.gson.annotations.SerializedName

//data class Student(
//    val id:String?,
//    val name:String?,
//    val bod:String?,
//    val phone:String?,
//    val photoUrl:String?
//)

data class Student(
    val id:String?,
    @SerializedName("student_name")
    val name:String?,
    @SerializedName("birth_of_date")
    val bod:String?,
    val phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String
)
