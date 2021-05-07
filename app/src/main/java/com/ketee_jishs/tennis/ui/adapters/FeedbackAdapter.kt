package com.ketee_jishs.tennis.ui.adapters

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketee_jishs.tennis.databinding.RecyclerItemFeedbackBinding
import com.ketee_jishs.tennis.retrofit.Feedbacks

class FeedbackAdapter(
    private var data: ArrayList<Feedbacks> = arrayListOf()
) : RecyclerView.Adapter<FeedbackAdapter.ViewHolder>() {

    fun replaceData(data: ArrayList<Feedbacks>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemFeedbackBinding.inflate(
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
        private var binding: RecyclerItemFeedbackBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Feedbacks) {
            binding.feedback = data
            binding.executePendingBindings()
            binding.givenScore.text = "${data.givenRating} *"
            binding.commentatorPhoto.setImageURI(Uri.parse(data.commentatorPhoto))
            if (adapterPosition == itemCount - 1) {
                binding.separationLine.visibility = View.GONE
            }
        }
    }
}