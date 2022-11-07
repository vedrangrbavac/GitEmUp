package com.example.gitemup.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.gitemup.R
import com.example.gitemup.common.base.BaseFragment
import com.example.gitemup.databinding.FragmentHomeBinding
import com.example.gitemup.ui.adapters.RepositoriesRecyclerViewAdapter
import com.example.gitemup.viewmodels.RepositoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<RepositoryViewModel>() {

    override val viewModel: RepositoryViewModel by viewModel()

    private lateinit var binding: FragmentHomeBinding

    private val repositoriesRecyclerViewAdapter =
        RepositoriesRecyclerViewAdapter(listener = { item, _ ->
            run {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.owner.url))
                startActivity(intent)
            }
        },
            sublistener = { item, _ ->
                viewModel.navigateToRepositoryDetails(item)
            })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ).apply {
            lifecycleOwner = this@HomeFragment.viewLifecycleOwner
            viewModel = this@HomeFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvRepositories.adapter = repositoriesRecyclerViewAdapter


        // "GitEmUp+in:name"

        val spinner = binding.spSort
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sort,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        )
        spinner.adapter = spinnerAdapter


    }

}