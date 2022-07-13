package com.udacity.shoestore.ui.screens.shoe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.adapters.ShoeAdapter
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import timber.log.Timber

class ShoeListFragment : Fragment() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////// {BINDING}
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////// {LOGIC}
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private val viewModel by activityViewModels<ShoeListViewModel>()
    private val adapter by lazy { ShoeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setupRecyclerView()

            fabDetail.setOnClickListener {
                val action =
                    ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment(null)
                findNavController().navigate(action)
            }
            viewModel.shoeList.observe(viewLifecycleOwner) {
                Timber.i("shoe list: $it")
                adapter.submitList(it)
            }
        }
    }

    private fun FragmentShoeListBinding.setupRecyclerView() {
        adapter.apply {
            rvShoe.adapter = this

            setOnItemClickListener {
                val action =
                    ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment(it)
                findNavController().navigate(action)
            }
        }
    }


}