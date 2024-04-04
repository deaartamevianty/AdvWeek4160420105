package com.example.advweek4160420105.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4160420105.databinding.StudentListItemBinding
import com.example.advweek4160420105.model.Student
import com.example.advweek4160420105.util.loadImage
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):StudentViewHolder {
        val binding = StudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)

    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.txtID.text = studentList[position].id
        holder.binding.txtName.text = studentList[position].name

        holder.binding.imageStudent.loadImage(studentList[position].photoUrl.toString(), holder.binding.progressImage)

        holder.binding.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(studentList[position].photoUrl).into(holder.imgPhoto)

        picasso.build().load(studentList[position].photoUrl)
            .into(holder.imgPhoto, object:Callback {
                override fun onSuccess() {
                    holder.binding.progressImage.visibility = View.INVISIBLE
                    holder.binding.imageStudent.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }

            })

    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return studentList.size
    }

    class StudentViewHolder(var binding: StudentListItemBinding)
        :RecyclerView.ViewHolder(binding.root)
}

