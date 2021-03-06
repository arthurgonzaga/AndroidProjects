package br.com.androidmaster.roomwordssample.applications

import android.app.Application
import br.com.androidmaster.roomwordssample.data.local.word.WordRepository
import br.com.androidmaster.roomwordssample.data.local.word.WordRoomDB

class WordsApplication(): Application() {
    lateinit var database: WordRoomDB
    val repository by lazy { WordRepository(database.wordDao()) }
    override fun onCreate() {
        super.onCreate()
        database = WordRoomDB.getInstance(this)
    }
}