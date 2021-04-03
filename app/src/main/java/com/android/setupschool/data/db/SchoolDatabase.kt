package com.android.setupschool.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.setupschool.data.db.dao.StudentDao
import com.android.setupschool.data.db.dao.TeacherDao
import com.android.setupschool.data.db.entities.Student
import com.android.setupschool.data.db.entities.Teacher


@Database(entities = [Teacher::class, Student::class], version = 1)
abstract class SchoolDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun teacherDao(): TeacherDao
}