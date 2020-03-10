package com.poc.bruna.marvel.feature.character.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.poc.bruna.marvel.R
import com.poc.bruna.marvel.feature.base.business.data.Result
import com.poc.bruna.marvel.feature.base.view.BaseFragment
import com.poc.bruna.marvel.feature.search.business.data.CharacterData
import com.poc.bruna.marvel.feature.search.gateway.SearchViewModel
import com.poc.bruna.marvel.utils.extensions.activityViewModelProvider
import com.poc.bruna.marvel.utils.extensions.loadImage
import kotlinx.android.synthetic.main.fragment_character.*

class CharacterFragment : BaseFragment() {

    lateinit var viewModel: SearchViewModel
    lateinit var adapter: ComicsAdapter

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
        viewModel = activityViewModelProvider(viewModelFactory)
        viewModel.charactersLiveData.observe(viewLifecycleOwner, Observer {
            if (it is Result.Success) bind(it.data)
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
        adapter = ComicsAdapter()
        listComics.adapter = adapter
    }

    private fun setUpBackPressed() {
        backCharacter.setOnClickListener { requireActivity().onBackPressed() }
    }
}