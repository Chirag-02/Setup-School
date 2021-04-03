package com.android.setupschool.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.setupschool.data.db.entities.Student

@Dao
interface StudentDao {

    @Insert
    suspend fun createStudent(student: Student)

    @Query("DELETE from student WHERE rollNo = :rollNo")
    suspend fun deleteStudent(rollNo: Int)

    @Query("SELECT * FROM student")
    fun getAllStudents(): LiveData<List<Student>>
}