package com.suatzengin.catbreeds.presentation.favorites

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager

import com.suatzengin.catbreeds.databinding.FragmentFavoritesBinding
import com.suatzengin.catbreeds.domain.model.CatBreed
import com.suatzengin.catbreeds.presentation.favorites.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var adapter: FavoritesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        adapter = FavoritesAdapter({
            viewModel.isFavorited(it)
        }, { deletedFavoriteItem ->
            deleteFromFavorites(deletedFavoriteItem)
        })
        setupRecyclerView()
        observeFavoriteList()
        return binding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvFavorites
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 300
            removeDuration = 100
        }
    }

    private fun deleteFromFavorites(deletedFavoriteItem: CatBreed) {
        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder
            .setTitle("Delete From Favorites!")
            .setMessage("Are you sure to delete ${deletedFavoriteItem.name} from favorites?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                viewModel.handleEvent(FavoritesEvent.DeleteCatFromFavorites(deletedFavoriteItem))
                viewModel.handleEvent(FavoritesEvent.GetAllFavorites)
            })
            .setNegativeButton("No", null)
        alertBuilder.create().show()
    }

    private fun observeFavoriteList() {
        viewModel.handleEvent(FavoritesEvent.GetAllFavorites)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFavorites.collect { state ->
                    checkIfFavoritesEmpty(state.favoriteList)
                    state.isLoading.let { boolean ->
                        if (boolean) {
                            binding.progressBarForFavoriteList.visibility = View.VISIBLE
                        } else {
                            binding.progressBarForFavoriteList.visibility = View.GONE
                        }
                    }
                    if (state.error.isNotBlank()) {
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun checkIfFavoritesEmpty(list: List<CatBreed>){
        when(list.isEmpty()){
            true -> {
                binding.ifEmptyDatabase.visibility = View.VISIBLE
                binding.tvNoData.visibility = View.VISIBLE
                adapter.submitList(list)
            }
            false -> {
                binding.ifEmptyDatabase.visibility = View.GONE
                binding.tvNoData.visibility = View.GONE
                adapter.submitList(list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}