package br.com.androidmaster.tddspendtracker.local.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.androidmaster.tddspendtracker.local.dao.SpendDao
import br.com.androidmaster.tddspendtracker.local.entities.Spend

@Database(version = 1, entities = [Spend::class])
abstract class SpendsDatabase: RoomDatabase(){

    abstract fun getSpendDao(): SpendDao
}