package com.project.hackathon.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Check")
class Check (
    @PrimaryKey(autoGenerate = true) var id : Long,
    @ColumnInfo(name = "description") var description : String,
)