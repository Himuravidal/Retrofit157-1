package com.example.retrofit157_1.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit157_1.databinding.MarsItemListBinding
import com.example.retrofit157_1.model.pojo.MarsTerrain

class MarsAdapter : RecyclerView.Adapter<MarsAdapter.MarsVH>() {

    private var listMarsItem = listOf<MarsTerrain>()

    private var selectedItem  =  MutableLiveData<MarsTerrain>()
    fun selectedItem() = selectedItem

    fun update(list: List<MarsTerrain>){
        listMarsItem = list
        notifyDataSetChanged()
    }

    inner class MarsVH(private val binding : MarsItemListBinding) :
            RecyclerView.ViewHolder(binding.root) , View.OnClickListener {
                fun bind(marsTerrain: MarsTerrain) {
                    Glide.with(binding.imageView)
                            .load(marsTerrain.srcImg)
                            .centerCrop()
                            .into(binding.imageView)
                    itemView.setOnClickListener(this)
                }

        override fun onClick(v: View?) {
           selectedItem.value = listMarsItem[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsVH {
       return MarsVH(MarsItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsVH, position: Int) {
       val marsTerrain = listMarsItem[position]
        holder.bind(marsTerrain)
    }

    override fun getItemCount(): Int = listMarsItem.size

}