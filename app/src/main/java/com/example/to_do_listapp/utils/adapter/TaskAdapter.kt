package com.example.to_do_listapp.utils.adapter

import android.icu.text.Transliterator
import android.icu.text.Transliterator.Position
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_listapp.databinding.EachTodoItemBinding
import com.example.to_do_listapp.databinding.FragmentHomeBinding
import com.example.to_do_listapp.utils.Model.ToDoData

class TaskAdapter(private val list: MutableList<ToDoData>): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val TAG = "TaskAdapter"
    private var listener:TaskAdapterInterface? = null

    fun setListener(listener: TaskAdapterInterface){
        this.listener = listener
    }

    class TaskViewHolder(val binding: EachTodoItemBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        val binding =
            EachTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.todoTask.text= this.task

                Log.d(TAG, "onBindViewHolder: "+ this)
                binding.editTask.setOnClickListener{
                    listener?.onEditItemClicked(this, position)
                }

                binding.deleteTask.setOnClickListener{
                    listener?.onDeleteItemClicked(this, position)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface  TaskAdapterInterface{
        fun onDeleteItemClicked(toDoData: ToDoData, position:Int)
        fun onEditItemClicked(todoData: ToDoData, position:Int)
    }
}