package br.com.androidmaster.roomwordssample.viewmodels

import androidx.lifecycle.*
import br.com.androidmaster.roomwordssample.data.local.word.WordRepository
import br.com.androidmaster.roomwordssample.data.model.Word
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository): ViewModel() {

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}