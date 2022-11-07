package com.example.gitemup.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.gitemup.R
import com.example.gitemup.config.REPOSITORY_BUNDLE
import com.example.gitemup.data.models.domain.Item
import com.example.gitemup.databinding.FragmentRepositoryDetailBinding


class RepositoryDetailFragment : Fragment() {

    private lateinit var binding: FragmentRepositoryDetailBinding

    lateinit var repository: Item

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentRepositoryDetailBinding>(
            inflater,
            R.layout.fragment_repository_detail,
            container,
            false
        ).apply {
            lifecycleOwner = this@RepositoryDetailFragment.viewLifecycleOwner
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Item>(REPOSITORY_BUNDLE)?.let {
            repository = it
            binding.repository = repository
        }

        initListeners()
    }

    private fun initListeners() {
        binding.btnAuthorPage.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(repository.owner.url)))
        }
        binding.btnRepositoryPage.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(repository.url)))
        }
    }
}