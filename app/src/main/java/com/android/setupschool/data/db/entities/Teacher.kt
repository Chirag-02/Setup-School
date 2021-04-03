package com.android.setupschool.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Teacher(
    val name: String,
    @PrimaryKey val id: String,
    val subject: String
)