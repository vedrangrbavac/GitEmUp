package com.example.gitemup.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.gitemup.R
import com.example.gitemup.config.REPOSITORY_BUNDLE
import com.example.gitemup.data.models.domain.Item
import com.example.gitemup.databinding.FragmentHomeBinding
import com.example.gitemup.databinding.FragmentRepositoryDetailBinding


class RepositoryDetailFragment : Fragment() {

    private lateinit var binding: FragmentRepositoryDetailBinding

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
        arguments?.let {
            val repository = it.getParcelable<Item>(REPOSITORY_BUNDLE)
            binding.repository = repository
        }
    }


}