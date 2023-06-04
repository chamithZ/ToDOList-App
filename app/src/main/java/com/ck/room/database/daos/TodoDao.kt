package com.ck.room.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ck.room.database.entities.ToDo

@Dao

interface TodoDao {
    @Insert
    suspend fun insertTodo(todo: ToDo)

    @Delete
    suspend fun delete(todo: ToDo)

    @Query("SELECT * FROM Todo")
    fun getAllTodos():List<ToDo>
}