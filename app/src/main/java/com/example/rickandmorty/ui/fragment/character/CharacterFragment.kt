package com.example.rickandmorty.ui.fragment.character

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.data.model.characters.Character
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.geeks.rickandmorty.ui.fragment.character.CharacterAdapter
import com.geeks.rickandmorty.ui.fragment.character.OnClick
import com.example.rickandmorty.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment(), OnClick {

    private lateinit var binding: FragmentCharacterBinding
    private val viewModel: CharacterViewModel by viewModels()
    private lateinit var charactersAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserve()
    }

    private fun setupObserve() {
        viewModel.getCharacters().observe(viewLifecycleOwner) { resource ->
            when (resource){
                is Resource.Success -> viewLifecycleOwner.lifecycleScope.launch {
                    binding.pgCharacter.visibility = View.GONE
                    charactersAdapter.submitData(resource.data)
                }
                is Resource.Error -> {
                    binding.pgCharacter.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.pgCharacter.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initialize() {
        charactersAdapter = CharacterAdapter(this@CharacterFragment)
        binding.rvCharacter.adapter = charactersAdapter
    }

    override fun onClick(model: Character) {
        val action = CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(model.id)
        Log.e("TAG", "onClick: $model.id", )
        findNavController().navigate(action)
    }
}