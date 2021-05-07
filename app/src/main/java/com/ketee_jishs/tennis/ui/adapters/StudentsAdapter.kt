package com.ketee_jishs.tennis.ui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketee_jishs.tennis.databinding.RecyclerItemStudentBinding
import com.ketee_jishs.tennis.retrofit.Students

class StudentsAdapter(
    private var data: ArrayList<Students> = arrayListOf()
) : RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {

    fun replaceData(data: ArrayList<Students>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemStudentBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    @Suppress("DEPRECATION")
    inner class ViewHolder(
        private var binding: RecyclerItemStudentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Students) {
            binding.student = data
            binding.executePendingBindings()
            binding.studentPhoto.setImageURI(Uri.parse(data.studentPhoto))
        }
    }
}