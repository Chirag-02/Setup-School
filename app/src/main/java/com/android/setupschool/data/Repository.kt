package com.android.setupschool.data

import com.android.setupschool.data.db.SchoolDatabase
import com.android.setupschool.data.db.entities.Student
import com.android.setupschool.data.db.entities.Teacher
import javax.inject.Inject

class Repository @Inject constructor(
    private val db: SchoolDatabase,

    ) {
    val students get() = db.studentDao().getAllStudents()
    val teachers get() = db.teacherDao().getAllTeachers()


    suspend fun createStudent(student: Student) = db.studentDao().createStudent(student)
    suspend fun createTeacher(teacher: Teacher) = db.teacherDao().createTeacher(teacher)
    suspend fun deleteStudent(id: Int) = db.studentDao().deleteStudent(id)
    suspend fun deleteTeacher(teacherId: String) = db.teacherDao().deleteTeacher(teacherId)
}