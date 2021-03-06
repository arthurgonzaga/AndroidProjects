package br.com.androidmaster.roomwordssample.applications

import android.app.Application
import br.com.androidmaster.roomwordssample.data.local.word.WordRepository
import br.com.androidmaster.roomwordssample.data.local.word.WordRoomDB
import br.com.androidmaster.roomwordssample.di.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WordsApplication(): Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WordsApplication)
            modules(appModule)
        }
    }
}