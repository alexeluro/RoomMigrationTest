package com.inspiredcoda.roommigrationtest.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inspiredcoda.roommigrationtest.data.db.entity.StudentEntity

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewStudent(student: StudentEntity)

    @Query("SELECT * FROM student_entity")
    fun selectAllStudents(): LiveData<List<StudentEntity>>

}