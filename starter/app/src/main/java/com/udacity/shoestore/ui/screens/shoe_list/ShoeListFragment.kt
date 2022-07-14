package com.udacity.shoestore.ui.screens.shoe_list

import android.os.Bundle
import android.view.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
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
        setHasOptionsMenu(true)
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
    private val viewModel by activityViewModels<ShoeListViewModel>()
    // private val adapter by lazy { ShoeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            fabDetail.setOnClickListener {
                val action =
                    ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment(null)
                findNavController().navigate(action)
            }
            viewModel.shoeList.observe(viewLifecycleOwner) { shoeList ->
                Timber.i("shoe list: $shoeList")
                shoeList.forEach { shoe ->
                    addTextView(shoe)
                }
            }
        }
    }

    private fun FragmentShoeListBinding.addTextView(shoe: Shoe) {
        val textView = TextView(requireContext())
        textView.apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            shoe.apply {
                text = """
                    Name: $name
                    Size: $size
                    Company: $company
                    Description: $description
                """.trimIndent()
            }
            textView.setPadding(16,32,16,32)
            setTextAppearance(R.style.TextStylePadding)

            setOnClickListener {
                val action =
                    ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment(shoe)
                findNavController().navigate(action)
            }
        }

        if (textView.parent != null) {
            (textView.parent as ViewGroup).removeView(textView)
        }

        try {

        } catch (e:Exception) {
            Timber.e(e)
        }
    }


    /**
     * used to inflating menu to the activity and show it on the action bar
     * */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }


    /**
     * used to perform menu item on click listener
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.loginFragment -> {
                val action = ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}