package com.example.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.response.SimpleFilm
import com.example.lista_de_filmes_oficial.R
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class FilmesAdapter(private var items: MutableList<SimpleFilm>,private val listener: ((SimpleFilm, position: Int) -> Unit)) : RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        return FilmesViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_filmes, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        val filmesItemModel = items[position]
        holder.bind(filmesItemModel) { listener(filmesItemModel, position) }
    }

    fun updateList(list: List<SimpleFilm>) {
        items = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    class FilmesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val filmesImg: ImageView = itemView.findViewById(R.id.img_poster)
        private val filmesTitle: TextView = itemView.findViewById(R.id.txt_filmes_name)
        private val filmesYear: TextView = itemView.findViewById(R.id.txt_filmes_year)
        private val card: MaterialCardView = itemView.findViewById(R.id.card)
        private var id: String = "0"

        fun bind(filmesItem: SimpleFilm,onItemClickListener: () -> Unit) {
            filmesTitle.text = filmesItem.title
            filmesYear.text = filmesItem.year

            Picasso.get()
                .load(filmesItem.poster)
                .error(R.drawable.ic_launcher_background)
                .into(filmesImg)
            id = filmesItem.id
            card.setOnClickListener {
                onItemClickListener.invoke()
            }
        }
    }
}
