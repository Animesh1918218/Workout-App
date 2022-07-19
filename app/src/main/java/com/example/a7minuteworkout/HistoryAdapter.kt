package com.example.a7minuteworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.databinding.HistoryAdapterLayoutBinding

class HistoryAdapter(private val items:ArrayList<HistoryData>):RecyclerView.Adapter<HistoryAdapter.MainViewHolder>() {
    inner class MainViewHolder(item:HistoryAdapterLayoutBinding):RecyclerView.ViewHolder(item.root){
        val textrv = item.historText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(HistoryAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       holder.textrv.text = "${position+1} : ${items[position].date}"
    }

    override fun getItemCount(): Int {
        return items.size
    }
}