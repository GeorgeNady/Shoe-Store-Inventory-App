package com.udacity.shoestore.ui.screens.shoe_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.screens.shoe_list.ShoeListViewModel

class ShoeDetailFragment : Fragment() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////// {BINDING}
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private var _binding: FragmentShoeDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        return binding.root
    }

    /**
     * * used to destroy or clear all the generated binding views
     * * and data to avoid memory leak and conflicts
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////// {LOGIC}
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private val args by navArgs<ShoeDetailFragmentArgs>()
    private val mShoe by lazy { args.shoe }
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            bViewModel = viewModel
            mShoe?.let {
                viewModel.findShoeWithName(it)
            }

            btnSave.setOnClickListener {
                findNavController().navigateUp()
            }

            btnCancel.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}