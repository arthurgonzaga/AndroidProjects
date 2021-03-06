package br.com.androidmaster.roomwordssample.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    val mWord: String
)