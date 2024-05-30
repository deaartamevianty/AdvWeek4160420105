package com.example.advweek4160420105.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4160420105.R
import com.example.advweek4160420105.databinding.StudentListItemBinding
import com.example.advweek4160420105.model.Student
import com.squareup.picasso.Picasso

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(),
 ButtonDetailClickListener {
    class StudentViewHolder(var view: StudentListItemBinding):RecyclerView.ViewHolder(view.root)
    {
        val txtID:TextView
        val txtName:TextView
        val btnDetail:Button
        val imgPhoto:ImageView

        init {
            txtID = view.txtID
            txtName = view.txtName
            btnDetail = view.btnDetail
            imgPhoto = view.imgPhoto
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.student_list_item, parent,false)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studentList[position]
        holder.view.listener = this

//        holder.txtID.text = studentList[position].id
//        holder.txtName.text = studentList[position].name
//        holder.btnDetail.setOnClickListener {
//            val action = StudentListFragmentDirections.actionStudentDetailFragment()
//            Navigation.findNavController(it).navigate(action)
//        }

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(studentList[position].photoUrl).into(holder.imgPhoto)
    }

    fun updateStudentList(newStudentList:ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetailFragment(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}