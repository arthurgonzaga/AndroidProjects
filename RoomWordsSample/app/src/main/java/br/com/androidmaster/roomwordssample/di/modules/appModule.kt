package br.com.androidmaster.roomwordssample.di.modules

import androidx.room.Room
import br.com.androidmaster.roomwordssample.data.local.word.WordRepository
import br.com.androidmaster.roomwordssample.data.local.word.WordRoomDB
import br.com.androidmaster.roomwordssample.viewmodels.WordViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(androidApplication(),
            WordRoomDB::class.java,
            "word_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<WordRoomDB>().wordDao() }

    single { WordRepository(get()) }

    viewModel { WordViewModel(get()) }
}