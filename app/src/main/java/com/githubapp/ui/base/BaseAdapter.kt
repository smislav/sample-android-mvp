package com.githubapp.ui.base

import android.support.v7.widget.RecyclerView

abstract class BaseAdapter<I, VH: RecyclerView.ViewHolder> :  RecyclerView.Adapter<VH>(){
    var items: ArrayList<I> = ArrayList()

    fun addItems(items: List<I>){
        this.items.addAll(items)
        notifyItemRangeInserted(itemCount - items.size,
                itemCount)
    }

    fun clearItems(){
        this.items.clear()
        notifyDataSetChanged()
    }
}