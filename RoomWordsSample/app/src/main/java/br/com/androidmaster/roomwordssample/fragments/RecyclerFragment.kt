package br.com.androidmaster.roomwordssample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import br.com.androidmaster.roomwordssample.R
import br.com.androidmaster.roomwordssample.viewmodels.WordViewModel
import br.com.androidmaster.roomwordssample.viewmodels.WordViewModelFactory
import br.com.androidmaster.roomwordssample.activities.MainActivity
import br.com.androidmaster.roomwordssample.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment(){

    lateinit var binding: FragmentRecyclerBinding

    val viewModel: WordViewModel by activityViewModels{
        WordViewModelFactory(MainActivity.application.repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerBinding.inflate(inflater)
        if(viewModel.isEmpty()){
            binding.empty.visibility = View.VISIBLE
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.fab.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}