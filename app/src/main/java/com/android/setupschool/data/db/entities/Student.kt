package com.android.setupschool.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    val name: String,
    @PrimaryKey val rollNo: Int,
    val className: String,
    val section: String,
    val profilePic: String? = null
)