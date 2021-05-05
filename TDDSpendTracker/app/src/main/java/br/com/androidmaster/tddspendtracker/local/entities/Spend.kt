package br.com.androidmaster.tddspendtracker.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spend_table")
data class Spend(val amount: Int, val title: String){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}