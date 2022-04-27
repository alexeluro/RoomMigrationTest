package com.inspiredcoda.roommigrationtest.presentation.student_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inspiredcoda.roommigrationtest.data.db.entity.StudentEntity
import com.inspiredcoda.roommigrationtest.databinding.StudentListItemBinding

class StudentListAdapter() : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    private var students: MutableList<StudentEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = StudentListItemBinding.inflate(inflater)
        return StudentViewHolder(root)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        if (students.isNotEmpty()) {
            holder.viewItem.studentItemName.text = students[position].name
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }

    fun submitStudents(newStudents: List<StudentEntity>) {
        if (newStudents.isNotEmpty()) {
            students.clear()
            students.addAll(newStudents)
            notifyDataSetChanged()
        }
    }

    inner class StudentViewHolder(val viewItem: StudentListItemBinding) :
        RecyclerView.ViewHolder(viewItem.root) {

    }

}