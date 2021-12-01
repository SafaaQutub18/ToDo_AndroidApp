package com.example.todoapp

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.RowRecyclerviewBinding

class RecyclerViewAdapter(val toDoList: ArrayList<ToDo>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    //comment for my self +.+: RowRecyclerviewBinding: automatically generated after i created row_recyclerview.xml file
    class ItemViewHolder(val binding: RowRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(RowRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    //add listener to do many things when checked/unchecked of checkbox changed
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val list = toDoList[position]
        var myCheckbox = holder.binding.checkBox
        myCheckbox.isChecked = list.completed
        myCheckbox.setText(toDoList[position].taskTitle)

        myCheckbox.setOnCheckedChangeListener{_,isCheckeds->
            if(isCheckeds)
                myCheckbox.setTextColor(Color.GRAY)
            else {
                myCheckbox.setTextColor(Color.BLACK)
            }
            list.completed = !list.completed

        }
    }

    override fun getItemCount()= toDoList.size

    fun deleteTask(){
        toDoList.removeAll{ task ->
            task.completed
        }
        notifyDataSetChanged()
    }
}
