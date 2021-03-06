package br.com.androidmaster.roomwordssample.data.local.word

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.androidmaster.roomwordssample.data.model.Word

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
abstract class WordRoomDB: RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object{
        private var INSTANCE: WordRoomDB? = null

        fun getInstance(context: Context): WordRoomDB {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context,
                        WordRoomDB::class.java,
                        "word_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}