package br.com.androidmaster.roomwordssample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.androidmaster.roomwordssample.viewmodels.WordViewModel
import br.com.androidmaster.roomwordssample.databinding.FragmentAddWordBinding

class AddWordFragment : Fragment() {

    lateinit var binding: FragmentAddWordBinding

    private lateinit var viewModel: WordViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWordBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}