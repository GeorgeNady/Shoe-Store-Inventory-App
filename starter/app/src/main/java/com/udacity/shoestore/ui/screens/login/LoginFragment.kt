package com.udacity.shoestore.ui.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginFragment : Fragment() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////// {BINDING}
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        setHasOptionsMenu(false)
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
    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            bViewModel = viewModel
            btnLogin.setOnClickListener { navigateToWelcomeFragment() }
            btnCreate.setOnClickListener { navigateToWelcomeFragment() }

           lifecycleScope.launch {
               viewModel.isSubmitEnabled.collectLatest {
                   btnLogin.isEnabled = it
                   btnCreate.isEnabled = it
               }
           }
        }
    }

    /**
     * #### navigate from login to welcome
     * #### created to avoid repetition
     */
    private fun navigateToWelcomeFragment() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }

}