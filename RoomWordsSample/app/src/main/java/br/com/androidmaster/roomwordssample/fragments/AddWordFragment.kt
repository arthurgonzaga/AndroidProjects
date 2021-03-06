package br.com.androidmaster.roomwordssample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import br.com.androidmaster.roomwordssample.data.model.Word
import br.com.androidmaster.roomwordssample.viewmodels.WordViewModel
import br.com.androidmaster.roomwordssample.databinding.FragmentAddWordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddWordFragment : Fragment(), LifecycleObserver{

    lateinit var binding: FragmentAddWordBinding

    private val viewModel: WordViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddWordBinding.inflate(inflater)
        lifecycle.addObserver(this)
        return binding.root
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){

        binding.save.setOnClickListener {

            val word = binding.edtWord.text.toString()

            viewModel.insert(Word(word))
        }

        lifecycle.removeObserver(this)
    }
}