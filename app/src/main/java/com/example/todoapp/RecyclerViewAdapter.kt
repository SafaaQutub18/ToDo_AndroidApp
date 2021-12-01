package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.RowRecyclerviewBinding

class RecyclerViewAdapter(val toDoList: ArrayList<ToDo>,listener : checkBoxDelegate) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    var delegate : checkBoxDelegate = listener
    //comment for my self +.+: RowRecyclerviewBinding: automatically generated after i created row_recyclerview.xml file
    class ItemViewHolder(val binding: RowRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(RowRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    //add listener to do many things when checked/unchecked of checkbox changed
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var myCheckbox = holder.binding.checkBox

        myCheckbox.setText(toDoList[position].taskTitle)

        myCheckbox.setOnCheckedChangeListener{compoundButton,b->
            if(myCheckbox.isChecked)
                myCheckbox.setTextColor(Color.GRAY)
            else
                myCheckbox.setTextColor(Color.BLACK)
            delegate.chechClicked(myCheckbox.isChecked,position)

        }
    }

    override fun getItemCount()= toDoList.size
}

interface checkBoxDelegate{
    fun chechClicked(isCheckedValue:Boolean, position: Int)
}