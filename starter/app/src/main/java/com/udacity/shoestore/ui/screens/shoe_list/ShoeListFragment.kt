package com.udacity.shoestore.ui.screens.shoe_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import kotlinx.android.synthetic.main.fragment_instruction.*
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
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_list,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////// {LOGIC}
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private val viewModel by viewModels<ShoeListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            fabDetail.setOnClickListener {
                val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
                findNavController().navigate(action)
            }
            viewModel.shoeList.observe(viewLifecycleOwner) {
                Timber.i("shoe list: $it")
            }
        }
    }

}