package com.ck.room.adapter

import android.content.Context
import android.text.method.Touch
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ck.room.R
import com.ck.room.database.TodoDatabase
import com.ck.room.database.entities.ToDo
import com.ck.room.database.repositories.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Todoadapter: RecyclerView.Adapter<Todoadapter.ViewHolder>()  {
    lateinit var data: List<ToDo>
    lateinit var context: Context

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){

        val cbTodo: CheckBox
        val Deletes: ImageView


        init{
            cbTodo=view.findViewById(R.id.checkBox)
            Deletes = view.findViewById(R.id.delete)

        }
    }


    fun setData(data: List<ToDo>, context: Context) {
        this.data = data
        this.context = context
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_view_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cbTodo.text = data[position].item
        holder.Deletes.setOnClickListener {
            if(holder.cbTodo.isChecked){
                val repository = TodoRepository(TodoDatabase.getInstance(context))
                holder.Deletes.setImageResource(R.drawable.dellete)
                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(data[position])
                    val data = repository.getAllTodos()
                    withContext(Dispatchers.Main) {
                        setData(data, context)
                        holder.Deletes.setImageResource(R.drawable.dellete)
                    }
                }
            }else{
                Toast.makeText(context,"Cannot delete unmarked Todo items",Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
    override fun getItemCount() = data.size
}



