package br.com.androidmaster.roomwordssample.data.local.word

import androidx.annotation.WorkerThread
import br.com.androidmaster.roomwordssample.data.model.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAllWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }
}