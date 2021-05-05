package br.com.androidmaster.tddspendtracker

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.lifecycle.ApplicationLifecycleMonitor
import br.com.androidmaster.tddspendtracker.local.dao.SpendDao
import br.com.androidmaster.tddspendtracker.local.data.SpendsDatabase
import br.com.androidmaster.tddspendtracker.local.entities.Spend
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class SpendsDatabaseTests: TestCase(){

    private lateinit var db: SpendsDatabase
    private lateinit var dao: SpendDao

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SpendsDatabase::class.java).build()
        dao = db.getSpendDao()
    }

    @After
    fun closeDB(){
        db.close()
    }

    @Test
    fun writeAndReadSpend() = runBlocking {
        val spend = Spend(4, "chocolate")
        dao.insert(spend)
        val result = dao.getAll()
        assertThat(result.contains(spend)).isTrue()
    }
}