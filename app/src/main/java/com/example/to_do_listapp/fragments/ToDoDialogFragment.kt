package com.example.to_do_listapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.to_do_listapp.databinding.FragmentToDoDialogBinding
import com.example.to_do_listapp.utils.Model.ToDoData


import com.google.android.material.textfield.TextInputLayout


class ToDoDialogFragment : DialogFragment() {

    private lateinit var binding:FragmentToDoDialogBinding
    private var listener : OnDialogNextBtnClickListener? = null
    private var toDoData: ToDoData? = null


    fun setListener(listener: OnDialogNextBtnClickListener) {
        this.listener = listener
    }

    companion object {
        const val TAG = "DialogFragment"
        @JvmStatic
        fun newInstance(taskId: String, task: String) =
            ToDoDialogFragment().apply {
                arguments = Bundle().apply {
                    putString("taskId", taskId)
                    putString("task", task)
                }
            }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentToDoDialogBinding.inflate(inflater , container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (arguments != null){

            toDoData = ToDoData(arguments?.getString("taskId").toString() ,arguments?.getString("task").toString())
            binding.todoEt.editText?.setText(toDoData?.task)
        }



        binding.todoClose.setOnClickListener {
            dismiss()
        }

        binding.todoNextButton.setOnClickListener {

            val todoTask = binding.todoEt.editText?.text.toString()
            if (todoTask.isNotEmpty()){
                if (toDoData == null){
                    listener?.saveTask(todoTask ,binding.todoEt)
                }else{
                    toDoData!!.task = todoTask
                    listener?.updateTask(toDoData!!,binding.todoEt)
                }

            }
        }
    }

    interface OnDialogNextBtnClickListener{
        fun saveTask(todoTask:String, todoEdit: TextInputLayout)
        fun updateTask(toDoData: ToDoData, todoEdit: TextInputLayout)
    }

}