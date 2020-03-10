package com.poc.bruna.marvel.feature.character.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.poc.bruna.marvel.R
import com.poc.bruna.marvel.feature.base.business.data.Result
import com.poc.bruna.marvel.feature.base.view.BaseFragment
import com.poc.bruna.marvel.feature.search.business.data.CharacterData
import com.poc.bruna.marvel.feature.search.gateway.SearchViewModel
import com.poc.bruna.marvel.utils.extensions.loadImage
import com.poc.bruna.marvel.utils.extensions.onBackPressed
import kotlinx.android.synthetic.main.fragment_character.*

class CharacterFragment : BaseFragment() {

    private val viewModel: SearchViewModel by activityViewModels { viewModelFactory }
    private val adapter: ComicsAdapter = ComicsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpList()
        setUpViewModel()
        setUpBackPressed()
    }

    private fun setUpViewModel() {
        viewModel.charactersLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> bind(it.data)
                is Result.Error, Result.Loading -> onBackPressed()
            }
        })
    }

    private fun bind(data: CharacterData) {
        with(data) {
            nameCharacter.text = name
            descriptionCharacter.text = description
            imageCharacter.loadImage(imageThumbnail)
            adapter.submitList(data.comics)
        }
    }

    private fun setUpList() {
        listComics.adapter = adapter
    }

    private fun setUpBackPressed() {
        backCharacter.setOnClickListener { requireActivity().onBackPressed() }
    }
}