package com.example.a7minuteworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.databinding.ExcersizeDisplayedItemsBinding

class ExcersizeItemAdapter( private val items:ArrayList<AllExcersizes>):RecyclerView.Adapter<ExcersizeItemAdapter.ViewHolder>() {
    inner class ViewHolder(private val bindings:ExcersizeDisplayedItemsBinding):RecyclerView.ViewHolder(bindings.root){
        val rvText = bindings.rvText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ExcersizeDisplayedItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val allExcersizes:AllExcersizes = items[position]
        holder.rvText.text = allExcersizes.getId().toString()
        when{
            allExcersizes.getIsCompleted()->{
                holder.rvText.setBackgroundColor(Color.WHITE)
            }
            allExcersizes.getIsSelected()->{
                holder.rvText.setBackgroundResource(R.drawable.recycler_selected_item_background)
            }
            else ->{
                holder.rvText.setBackgroundResource(R.drawable.recycler_view_item_background)
            }
        }

    }

    override fun getItemCount(): Int {
      return  items.size
    }

}