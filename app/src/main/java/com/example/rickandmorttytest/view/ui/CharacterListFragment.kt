package com.example.rickandmorttytest.view.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorttytest.R
import com.example.rickandmorttytest.databinding.CharacterListFragmentBinding
import com.example.rickandmorttytest.model.response.CharacterResponse
import com.example.rickandmorttytest.view.adapter.CharacterAdapter
import com.example.rickandmorttytest.view.adapter.CharacterListener
import com.example.rickandmorttytest.viewmodel.CharacterListViewModel
import com.google.android.material.snackbar.Snackbar

class CharacterListFragment : Fragment(), CharacterListener {

    private lateinit var binding: CharacterListFragmentBinding
    private lateinit var characterAdapter: CharacterAdapter

    private lateinit var viewModel: CharacterListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterListViewModel::class.java)

        viewModel.getCharacterRM("1")

        characterAdapter = CharacterAdapter(this)

        binding.rvCharacterList.layoutManager = LinearLayoutManager(context)
        binding.rvCharacterList.hasFixedSize()
        binding.rvCharacterList.adapter = characterAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.responseCharacters.observe(
            viewLifecycleOwner,
            { response ->
                Log.i("Characters Rick", response.toString())
                characterAdapter.updateData(response)
            }
        )

        viewModel.showMsgSnack.observe(viewLifecycleOwner, {
            if (it != null) {
                showMessage(it)
            }
        })
    }

    private fun showMessage(msg: String) {
        val snak = view?.let { Snackbar.make(it, "ok", Snackbar.LENGTH_SHORT) }
        snak?.show()
    }

}