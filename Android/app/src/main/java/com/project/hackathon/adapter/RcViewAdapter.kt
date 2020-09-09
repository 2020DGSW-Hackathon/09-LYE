package com.project.hackathon.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.hackathon.ContentActivity
import com.project.hackathon.MainActivity
import com.project.hackathon.R
import com.project.hackathon.data.Check
import com.project.hackathon.room.DataBase

class RcViewAdapter : RecyclerView.Adapter<RcViewAdapter.Holder>(){
    lateinit var checkList : ArrayList<Check>
    var checkDB : DataBase? = null

    fun setList(list : ArrayList<Check>){
        if(::checkList.isInitialized) return
        checkList = list
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val des = itemView.findViewById<TextView>(R.id.description)
        fun bind(check : Check) {
            des.text = check.description

            itemView.setOnClickListener {
                val i = Intent(itemView.context, ContentActivity::class.java)
                i.putExtra("des", check.description)
                i.putExtra("id", check.id)
                itemView.context.startActivity(i)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return checkList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(checkList[position])
    }

}