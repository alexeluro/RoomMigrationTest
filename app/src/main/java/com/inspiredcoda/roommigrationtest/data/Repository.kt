package com.inspiredcoda.roommigrationtest.data

import androidx.lifecycle.LiveData
import com.inspiredcoda.roommigrationtest.data.db.dao.StudentDao
import com.inspiredcoda.roommigrationtest.data.db.entity.StudentEntity

class Repository(
    private val studentDao: StudentDao
) {

    suspend fun selectAllStudents(): LiveData<List<StudentEntity>> {
        return studentDao.selectAllStudents()
    }

    suspend fun insertStudent(studentName: String) {
        studentDao.insertNewStudent(StudentEntity(name = studentName))
    }

}