package com.example.rickandmorty.ui.fragment.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.utils.Resource

class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding
    private val viewModel: CharacterDetailViewModel by viewModel()
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListData()
    }

    private fun initListData() = with(binding) {
        Log.e("TAG", "initListData: ${args.id}", )
        viewModel.getCharacter(args.id).observe(viewLifecycleOwner){ resource ->
            when (resource) {
                is Resource.Success -> {
                    characterNameTextView.text = resource.data.name
                    characterStatusTextView.text = resource.data.status
                    characterGenderTextView.text = resource.data.gender
                    lastKnownLocationTextView.text = resource.data.location.name
                    headerImageView.load(resource.data.image)

                }
                is Resource.Error<*> -> {
                    Log.e("TAG", "initListData: ${resource.message}", )
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading<*> -> {
                    // Show loading state
                }

                else -> {}
            }
        }
    }
}