package com.undabot.jobfair.core.view

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes

import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class SpacesItemDecoration(@DimenRes private val spaceId: Int) : RecyclerView.ItemDecoration() {

    private var space: Int? = null

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (space == null) {
            space = parent.context.resources.getDimension(spaceId).roundToInt()
        }

        space?.let {
            outRect.right = it / 2
            outRect.left = it / 2
            outRect.bottom = it / 2
            outRect.top = it / 2
        }
    }
}