package com.inspiredcoda.roommigrationtest.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_entity")
class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id") var id: Long = 0,
    @ColumnInfo(name = "student_name") var name: String
)