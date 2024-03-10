package com.ewersson.firstapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ewersson.firstapp.databinding.ServicesItemBinding
import com.ewersson.firstapp.model.services

class adapterservices(private val context: Context, private val listServices: MutableList<services>):
    RecyclerView.Adapter<adapterservices.ServicesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val itemList =  ServicesItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ServicesViewHolder(itemList)
    }

    override fun getItemCount() = listServices.size

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        holder.imgServices.setImageResource(listServices[position].img!!)
        holder.txtService.text = listServices[position].name
    }

    inner class  ServicesViewHolder(binding: ServicesItemBinding): RecyclerView.ViewHolder(binding.root){
        val imgServices = binding.imgServices
        val txtService = binding.txtServices
    }
}