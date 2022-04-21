package com.suatzengin.catbreeds.presentation.webView

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.suatzengin.catbreeds.R
import com.suatzengin.catbreeds.databinding.FragmentCatBreedDetailBinding
import com.suatzengin.catbreeds.databinding.FragmentWebViewBinding


class WebViewFragment : Fragment() {

    private var _binding: FragmentWebViewBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<WebViewFragmentArgs>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar!!.title = args.catWebView.name
        setupWebView()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(){
        val webView = binding.webView
        webView.webViewClient = WebViewClient()
        webView.apply {
            loadUrl(args.catWebView.wikipediaUrl.toString())
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }

    }
}