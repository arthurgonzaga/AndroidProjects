package br.com.androidmaster.roomwordssample.viewmodels

import androidx.lifecycle.*
import br.com.androidmaster.roomwordssample.data.local.word.WordRepository
import br.com.androidmaster.roomwordssample.data.model.Word
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository): ViewModel() {

    private val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    fun isEmpty(): Boolean{
        if(allWords.value != null){
            if(allWords.value!!.size >= 0) return false
        }
        return true
    }

}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}