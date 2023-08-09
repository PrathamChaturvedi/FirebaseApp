package com.example.firebaseapp.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

class RightAlignedLinearLayoutManager(context: Context) : LinearLayoutManager(context) {

    override fun layoutDecoratedWithMargins(
        child: View,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        // Calculate the width of the RecyclerView
        val recyclerViewWidth = width

        // Calculate the width of the child view
        val childWidth = right - left

        // Calculate the right offset to align the child to the right
        val rightOffset = recyclerViewWidth - childWidth

        super.layoutDecoratedWithMargins(child, rightOffset, top, recyclerViewWidth, bottom)
    }
}