package com.example.todoapp

import androidx.appcompat.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var taskRV: RecyclerView
    lateinit var addTaskBtn : View
    var toDoList : ArrayList<ToDo> = ArrayList()
    lateinit var taskAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //find the id's
        taskRV= findViewById(R.id.recyclerView)
        addTaskBtn = findViewById(R.id.floatingAddButton)

        // Adapter setting
        taskAdapter= RecyclerViewAdapter(toDoList)
        taskRV.adapter = taskAdapter
        taskRV.layoutManager = LinearLayoutManager(this)

        // floatingactionbutton
        addTaskBtn.setOnClickListener{view->
            alertDialog()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var deletedTskNum = 0
        for(task in toDoList)
            if(task.completed)
                deletedTskNum++

        taskAdapter.deleteTask()

        Toast.makeText(this, "$deletedTskNum tasks deleted", Toast.LENGTH_LONG).show()
        return super.onOptionsItemSelected(item)
    }


    fun alertDialog() {

         val dialogBuilder = AlertDialog.Builder(this)
        	        // set up the input
        	        val input = EditText(this)
        	        // set the message of our alert dialog
        	        dialogBuilder.setMessage("Enter your Task")

        	            .setPositiveButton("Add", DialogInterface.OnClickListener {
            	                    dialog, id -> toDoList.add(ToDo(input.text.toString()))
            	            })
        	            // negative button text and action
        	            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
            	                    dialog, id -> dialog.cancel()
            	            })
               // create dialog box
        	        val alert = dialogBuilder.create()
        	        // title for alert dialog box
        	        alert.setTitle("New Task")
        	        alert.setView(input)
        	        // show alert dialog
        	        alert.show()


    }
}