package com.arthurgonzaga.rxdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.arthurgonzaga.rxdemo1.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val observable = Observable.just("Hello World from RxJava!")

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                Log.i(TAG, "doOnSubscribe invoked")
                compositeDisposable.add(it)
            }
            .doOnNext {
                Log.i(TAG, "doOnNext invoked")
                binding.txtView.text = it
            }
            .doOnComplete {
                Log.i(TAG, "doOnComplete invoked")

            }
            .doOnError {
                Log.e(TAG, "doOnError invoked", it)

            }
            .subscribe()
    }

    companion object {
        private const val TAG = "MainActivity"
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}