package com.sidukov.fitnesskittest.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.sidukov.fitnesskittest.R
import com.sidukov.fitnesskittest.domain.CurrentDay
import com.sidukov.fitnesskittest.domain.TimeStamp
import com.sidukov.fitnesskittest.domain.TrainingInfo

class ScheduleAdapter(
    private var scheduleList: List<TimeStamp>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val dayView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_current_day, parent, false)
        val trainingView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_training_info, parent, false)
        return if (getItemViewType(viewType) == 0) CurrentDayViewHolder(dayView)
        else TrainingInfoViewHolder(trainingView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            0 -> with(holder as CurrentDayViewHolder) {
                this.currentDay.text = (scheduleList[position] as CurrentDay).date
            }
            1 -> (holder as TrainingInfoViewHolder).apply {
                with(scheduleList[position] as TrainingInfo) {
                    holder.startDate.text = this.startTime
                    holder.endDate.text = this.endTime
                    holder.trainerName.text = this.trainer
                    holder.trainingName.text = this.trainingName
                    holder.location.text = this.location
                    try {
                        holder.marker.background = ColorDrawable(Color.parseColor(this.color))
                    } catch (e: IllegalArgumentException) {
                        holder.marker.background = ColorDrawable(Color.GREEN)
                    }
                }
            }
        }
    }

    class CurrentDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currentDay: TextView = itemView.findViewById(R.id.text_current_day)
    }

    class TrainingInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val startDate: TextView = itemView.findViewById(R.id.start_time)
        val endDate: TextView = itemView.findViewById(R.id.end_time)
        val trainingName: TextView = itemView.findViewById(R.id.text_training_name)
        val trainerName: TextView = itemView.findViewById(R.id.text_trainer)
        val location: TextView = itemView.findViewById(R.id.text_location_name)
        val marker: MaterialCardView = itemView.findViewById(R.id.view_marker)
    }

    override fun getItemCount() = scheduleList.size

    override fun getItemViewType(position: Int) = if (scheduleList[position] is CurrentDay) 0 else 1

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<TimeStamp>) {
        scheduleList = newList
        notifyDataSetChanged()
    }

}