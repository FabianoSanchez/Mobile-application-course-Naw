package com.example.gamebacklog.ui.mainActivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import kotlinx.android.synthetic.main.list_view.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent, false)
        )
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(games[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
           val date = SimpleDateFormat("dd MMMM yyyy",Locale.ENGLISH).format(game.releaseDate)
           itemView.tvTitle.text = game.title
           itemView.tvPlatform.text = game.platform
           itemView.tvRelease.text = context.getString(R.string.release,date)
        }
    }
}