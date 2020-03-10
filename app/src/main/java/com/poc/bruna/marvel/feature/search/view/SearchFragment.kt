package com.poc.bruna.marvel.feature.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.poc.bruna.marvel.R
import com.poc.bruna.marvel.feature.base.business.data.Result
import com.poc.bruna.marvel.feature.base.view.BaseFragment
import com.poc.bruna.marvel.feature.search.gateway.SearchViewModel
import com.poc.bruna.marvel.utils.extensions.hide
import com.poc.bruna.marvel.utils.extensions.hideKeyboard
import com.poc.bruna.marvel.utils.extensions.navigate
import com.poc.bruna.marvel.utils.extensions.onBackPressed
import com.poc.bruna.marvel.utils.extensions.show
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment() {

    private val viewModel: SearchViewModel by activityViewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpViews()
        onBackPressed { activity?.finish() }
    }

    private fun setUpViewModel() {
        viewModel.searchLiveData.observe(viewLifecycleOwner, Observer {
            if (it.getContentIfNotHandled() != null) {
                when (it.peekContent()) {
                    is Result.Loading -> loadingSearch.show()
                    is Result.Success -> navigateToCharacter()
                    is Result.Error -> showError(it.peekContent() as Result.Error)
                }
            }
        })
    }

    private fun navigateToCharacter() {
        loadingSearch.hide()
        navigate(R.id.action_to_character)
    }

    private fun showError(error: Result.Error) {
        loadingSearch.hide()
        buttonSearch.isEnabled = true
        showError(error.exception.message)
    }

    private fun setUpViews() {
        loadingSearch.hide()
        buttonSearch.isEnabled = true
        buttonSearch.setOnClickListener {
            searchByName()
        }
        textSearch.doOnTextChanged { text, _, _, _ ->
            buttonSearch.isEnabled = !text.isNullOrEmpty()
        }

        textSearch.setOnEditorActionListener { _, _, _ ->
            searchByName()
            true
        }
    }

    private fun searchByName() {
        buttonSearch.isEnabled = false
        activity.hideKeyboard()
        viewModel.searchByName(textSearch.text.toString())
    }

    override val idToShowInfoBar: View?
        get() = containerSearch
}