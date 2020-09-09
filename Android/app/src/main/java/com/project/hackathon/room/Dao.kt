package com.project.hackathon.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.project.hackathon.data.Check

@Dao
interface Dao {
    @Query("SELECT * FROM `Check`")
    fun getAll(): List<Check>

    @Insert
    fun insert(check : Check)

    @Query("UPDATE `Check` SET description = :description WHERE id = :id")
    fun update(description : String, id : Long)

    @Query("DELETE FROM `Check` WHERE id = :id")
    fun delete(id : Long)

}