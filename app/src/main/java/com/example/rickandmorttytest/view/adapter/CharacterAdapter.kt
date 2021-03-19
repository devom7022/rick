package com.example.rickandmorttytest.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorttytest.R
import com.example.rickandmorttytest.app.App
import com.example.rickandmorttytest.app.GlideApp
import com.example.rickandmorttytest.model.response.Results


class CharacterAdapter(private val listener: CharacterListener) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    var list = ArrayList<Results>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPicture: ImageView = itemView.findViewById(R.id.ivPicture)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        val tvSpecies: TextView = itemView.findViewById(R.id.tvSpecies)
        val tvGender: TextView = itemView.findViewById(R.id.tvGender)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val temp = list[position]
        holder.tvName.text = temp.name
        holder.tvSpecies.text = temp.species
        holder.tvGender.text = temp.gender
        holder.tvStatus.text = temp.status

        GlideApp.with(App.instance)
            .load(temp.image)
            .fitCenter()
            .into(holder.ivPicture)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(data: List<Results>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}