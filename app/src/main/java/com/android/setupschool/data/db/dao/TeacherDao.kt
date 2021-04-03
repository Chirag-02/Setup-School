package com.android.setupschool.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.setupschool.data.db.entities.Teacher

@Dao
interface TeacherDao {
    @Insert
    suspend fun createTeacher(teacher: Teacher)

    @Query("DELETE from teacher WHERE id = :teacherId")
    suspend fun deleteTeacher(teacherId: String)

    @Query("SELECT * FROM teacher")
    fun getAllTeachers(): LiveData<List<Teacher>>

}