package com.sidukov.fitnesskittest.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EmptyDividerItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        when ((parent.adapter as ScheduleAdapter).getItemViewType(position)) {
            0 -> {
                outRect.top = 60
                outRect.bottom = 40
            }
            1 -> {
                outRect.bottom = 20
            }
        }
    }
}