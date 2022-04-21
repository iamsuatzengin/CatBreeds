package com.suatzengin.catbreeds.presentation.cat_list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.suatzengin.catbreeds.R
import com.suatzengin.catbreeds.data.local.FavoritesModel
import com.suatzengin.catbreeds.databinding.FragmentCatBreedListBinding
import com.suatzengin.catbreeds.domain.model.CatBreed
import com.suatzengin.catbreeds.domain.model.toFavoriteModel
import com.suatzengin.catbreeds.presentation.cat_list.adapter.CatBreedListAdapter
import com.suatzengin.catbreeds.presentation.favorites.FavoritesEvent
import com.suatzengin.catbreeds.presentation.favorites.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatBreedListFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentCatBreedListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CatBreedListAdapter

    private val viewModel: CatBreedListViewModel by viewModels()
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatBreedListBinding.inflate(inflater, container, false)
        adapter = CatBreedListAdapter( { cat ->
            onClickFavoriteButton(cat)
        }, { cat ->
            onClickDeleteFavorite(cat)
        })

        setupRecyclerView()
        observeData()

        // Set Menu
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun onClickDeleteFavorite(cat: CatBreed) {
        val deletedItem = cat.toFavoriteModel()
        favoritesViewModel.handleEvent(FavoritesEvent.DeleteCatFromFavorites(deletedItem))
    }

    private fun onClickFavoriteButton(cat: CatBreed) {
        val favoriteItem = cat.toFavoriteModel()
        favoritesViewModel.handleEvent(FavoritesEvent.AddToFavorites(favoriteItem))
        Toast.makeText(requireContext(), "eklendi ${favoriteItem.name}", Toast.LENGTH_SHORT).show()
    }

    private fun searchCatBreed(catBreed: String) {
        // hatalı -> arama yapınca listedeki itemlerin resimleri gelmiyor.
        viewModel.searchCatBreed(catBreed)
        viewModel.searchState.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)

        })
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    state.catBreeds.let { list ->
                        adapter.submitList(list)
                    }
                    state.isLoading.let { boolean ->
                        if (boolean) {
                            binding.progressBarForList.visibility = View.VISIBLE
                        } else {
                            binding.progressBarForList.visibility = View.GONE
                        }
                    }
                    if (state.error.isNotBlank()) {
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvCatBreeds
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.cat_breed_list_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorites -> actionListToFavorites()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actionListToFavorites() {
        val action = CatBreedListFragmentDirections.listToFavorites()
        findNavController().navigate(action)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            //searchCatBreed(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            //searchCatBreed(query)
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}