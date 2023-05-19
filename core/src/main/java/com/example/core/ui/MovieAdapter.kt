package com.example.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Movie
import com.bumptech.glide.Glide
import com.example.core.BuildConfig
import com.example.core.R
import com.example.core.databinding.ItemListMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {
    private val listData = ArrayList<Movie>()
    var onItemClick : ((Movie) -> Unit)? = null

    fun setData(newListData : List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data : Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.LOAD_PIC+data.pictureUrl)
                    .override(240,360)
                    .into(pictureMovieIv)
                titleTv.text = data.title
                releaseDateTv.text = data.releaseDate
                voteAverageTv.text = data.vote
                voteCountTv.text = data.voteCount
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}