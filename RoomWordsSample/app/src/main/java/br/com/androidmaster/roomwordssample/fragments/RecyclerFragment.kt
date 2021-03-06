package br.com.androidmaster.roomwordssample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.androidmaster.roomwordssample.R
import br.com.androidmaster.roomwordssample.adapters.WordRecyclerAdapter
import br.com.androidmaster.roomwordssample.data.model.Word
import br.com.androidmaster.roomwordssample.viewmodels.WordViewModel
import br.com.androidmaster.roomwordssample.databinding.FragmentRecyclerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecyclerFragment : Fragment(), LifecycleObserver{

    lateinit var binding: FragmentRecyclerBinding

    private val viewModel: WordViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerBinding.inflate(inflater)




        lifecycle.addObserver(this)
        return binding.root
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        viewModel.allWords.observe(viewLifecycleOwner, Observer { list ->
            initRecyclerView(list)
            if(list.size > 0){
                binding.empty.visibility = View.INVISIBLE
            }else{
                binding.empty.visibility = View.VISIBLE
            }
        })
        binding.fab.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        lifecycle.removeObserver(this)
    }

    fun initRecyclerView(list: List<Word>){
        binding.rvWords.adapter = WordRecyclerAdapter(list)
        binding.rvWords.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWords.adapter?.notifyDataSetChanged()
    }
}

