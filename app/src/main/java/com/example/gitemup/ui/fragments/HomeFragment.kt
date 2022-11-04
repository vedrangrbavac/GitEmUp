package com.example.gitemup.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.gitemup.R
import com.example.gitemup.common.base.BaseFragment
import com.example.gitemup.databinding.FragmentHomeBinding
import com.example.gitemup.viewmodels.RepositoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment<RepositoryViewModel>() {

    override val viewModel: RepositoryViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ).apply {
            lifecycleOwner = this@HomeFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val button = view.findViewById<Button>(R.id.btnGetRepos)
        button.setOnClickListener {
            viewModel.callGetRepositories("GitEmUp+in:name")
        }



        viewModel.repositories.observe(viewLifecycleOwner) {
            it?.let {
                Timber.d(it.toString())
            }
        }


    }

}