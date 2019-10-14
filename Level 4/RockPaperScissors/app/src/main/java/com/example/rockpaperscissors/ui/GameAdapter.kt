package com.example.rockpaperscissors.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.game_view.view.*
import kotlinx.android.synthetic.main.game_view.view.tvCurrentResult

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_view, parent, false)
        )
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(games[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            when(game.result){
                draw -> itemView.tvCurrentResult.text = context.getString(R.string.draw_text)
                win -> itemView.tvCurrentResult.text = context.getString(R.string.win_text)
                loss -> itemView.tvCurrentResult.text = context.getString(R.string.loss_text)
            }

            itemView.tvTimeStamp.text = game.timeStamp.toString()
            itemView.ivRecordPlayer.setImageDrawable(context.getDrawable(game.playerResId))
            itemView.ivRecordComputer.setImageDrawable(context.getDrawable(game.computerResId))
        }
    }
}