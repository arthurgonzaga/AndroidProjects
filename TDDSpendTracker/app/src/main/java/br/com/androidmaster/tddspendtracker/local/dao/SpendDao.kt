package br.com.androidmaster.tddspendtracker.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.androidmaster.tddspendtracker.local.entities.Spend

@Dao
interface SpendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spend: Spend)

    @Query("SELECT * FROM spend_table")
    fun getAll(): List<Spend>
}