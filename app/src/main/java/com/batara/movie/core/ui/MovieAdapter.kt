package com.batara.movie.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batara.movie.R
import com.batara.movie.core.domain.model.Movie
import com.batara.movie.databinding.ItemListMovieBinding
import com.bumptech.glide.Glide

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
                    .load(data.pictureUrl)
                    .into(pictureMovieIv)
                titleTv.text = data.title
                releaseDateTv.text = data.releaseDate
                voteAverageTv.text = data.vote
                voteCountTv.text = data.voteCount
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}