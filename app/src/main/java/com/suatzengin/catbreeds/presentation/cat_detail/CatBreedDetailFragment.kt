package com.suatzengin.catbreeds.presentation.cat_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.navArgs
import com.suatzengin.catbreeds.R
import com.suatzengin.catbreeds.data.local.FavoritesModel
import com.suatzengin.catbreeds.databinding.FragmentCatBreedDetailBinding
import com.suatzengin.catbreeds.domain.model.CatBreed
import com.suatzengin.catbreeds.domain.model.toFavoriteModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatBreedDetailFragment : Fragment() {
    private var _binding: FragmentCatBreedDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<CatBreedDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatBreedDetailBinding.inflate(inflater, container, false)

        binding.cat = args.cat
        binding.favorite = args.favorites


        binding.tvTemperament.text = HtmlCompat.fromHtml(
            getString(R.string.temperament, args.cat!!.temperament),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        (activity as AppCompatActivity).supportActionBar!!.title = args.cat!!.name
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}