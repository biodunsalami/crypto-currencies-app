package com.example.cryptocurrencies.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


class BaseViewHolder<T> internal constructor(
    private val binding: ViewBinding,
    private val experssion: (T, ViewBinding) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        experssion(item, binding)
    }
}


class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    var listOfItems: List<T>? = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var expressionViewHolderBinding: ((T, ViewBinding) -> Unit)? = null
    var expressionOnCreateViewHolder: ((ViewGroup) -> ViewBinding)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return expressionOnCreateViewHolder?.let { it(parent) }
            ?.let { BaseViewHolder(it, expressionViewHolderBinding!!) }!!
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(listOfItems!![position])
    }

    override fun getItemCount(): Int {
        return listOfItems!!.size
    }
}
