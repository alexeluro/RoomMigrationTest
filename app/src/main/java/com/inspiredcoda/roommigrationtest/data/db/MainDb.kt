package com.inspiredcoda.roommigrationtest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.inspiredcoda.roommigrationtest.data.db.dao.StudentDao
import com.inspiredcoda.roommigrationtest.data.db.entity.StudentEntity

@Database(
    entities = [StudentEntity::class],
    version = 1
)
abstract class MainDb : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {
        private var dbInstance: MainDb? = null

        fun getDatabase(applicationContext: Context): MainDb {
            return synchronized(true) {
                if (dbInstance == null) {
                    dbInstance = Room.databaseBuilder(
                        applicationContext,
                        MainDb::class.java,
                        "student_db"
                    )
                        .build()
                }
                dbInstance!!
            }
        }
    }

}