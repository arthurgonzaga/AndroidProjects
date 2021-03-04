package br.com.androidmaster.roomwordssample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert
    fun insert(word: Word)

    @Query("SELECT * FROM word_table ORDER BY name ASC")
    fun getAllWords(): Flow<List<Word>>

    @Query("DELETE FROM word_table")
    fun deleteAll()
}