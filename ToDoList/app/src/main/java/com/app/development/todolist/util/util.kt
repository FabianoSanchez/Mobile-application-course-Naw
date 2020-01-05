package com.app.development.todolist.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.View
import com.app.development.todolist.R
import com.app.development.todolist.model.EventItem
import com.app.development.todolist.model.EventList
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class Util{

    companion object {

        /**
         * @param view         View to animate
         * @param toVisibility Visibility at the end of animation
         * @param toAlpha      Alpha at the end of animation
         * @param duration     Animation duration in ms
         */
        fun animateView(view: View, toVisibility: Int, toAlpha: Float, duration: Long) {
            val show = toVisibility == View.VISIBLE
            if (show) {
                view.alpha = 0F
            }
            view.visibility = View.VISIBLE
            view.animate()
                .setDuration(duration)
                .alpha(if (show) toAlpha else 0F)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        view.visibility = toVisibility
                    }
                })
        }

        fun groupListEventByDate(events:List<EventItem>): List<EventList>{
            return events.groupBy { checkValidDate(it)}.entries.map { (date,group) ->
                EventList(group,date)
            }.toList()
        }


        fun getEventTime(eventItem: EventItem,context: Context): String{
           return if(eventItem.start.dateTime == null || eventItem.end.dateTime == null){
               context.getString(R.string.all_day)
            }else{
                val startTime = eventItem.start.dateTime!!.subSequence(11,16)
                val endTime = eventItem.end.dateTime!!.subSequence(11,16)
               context.getString(R.string.event_time,startTime,endTime)
            }
        }

        private fun checkValidDate(eventItem:EventItem):String{
            return if(eventItem.start.dateTime == null){
                return eventItem.start.date!!
            }else{
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                formatter.format(LocalDateTime.parse(eventItem.start.dateTime!!.subSequence(0,19)))
            }
        }

        fun daySuffixForDate(date:Int):String{
            return when(date){
                1,21,31 -> "st"
                2,22 -> "nd"
                3,23 -> "rd"
                else -> "th"
            }
        }
    }
}