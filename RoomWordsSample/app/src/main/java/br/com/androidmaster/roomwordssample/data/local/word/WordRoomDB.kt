package br.com.androidmaster.roomwordssample.data.local.word

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.androidmaster.roomwordssample.data.model.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDB: RoomDatabase() {
    abstract fun wordDao(): WordDao
}