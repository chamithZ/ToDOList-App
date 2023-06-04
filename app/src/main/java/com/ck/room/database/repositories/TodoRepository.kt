package com.ck.room.database.repositories

import com.ck.room.database.TodoDatabase
import com.ck.room.database.entities.ToDo

class TodoRepository (
    private val db: TodoDatabase
){
    suspend fun insert(todo: ToDo)= db.getTodoDao().insertTodo(todo)

    suspend fun delete(todo: ToDo) = db.getTodoDao().delete(todo)

    fun getAllTodos()= db.getTodoDao().getAllTodos()
}