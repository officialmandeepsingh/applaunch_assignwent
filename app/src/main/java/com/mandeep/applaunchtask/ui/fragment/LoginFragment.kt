package com.mandeep.applaunchtask.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mandeep.applaunchtask.R
import com.mandeep.applaunchtask.databinding.FragmentLoginBinding
import com.mandeep.applaunchtask.util.extension.isEmail
import com.mandeep.applaunchtask.util.extension.isValidPassword
import com.mandeep.applaunchtask.util.extension.onClick
import com.mandeep.applaunchtask.util.extension.showErrorMessage

class LoginFragment : Fragment() {
    private val TAG = LoginFragment::class.java.simpleName
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignIn onClick {
            clearError()
            if (validateInputFields()) {
                findNavController().navigate(R.id.action_loginFragment_to_userListFragment)
            }
        }

    }

    private fun clearError() {
        binding.apply {
            txtInputUsername.error = null
            txtInputPassword.error = null
        }
    }

    private fun validateInputFields(): Boolean {
        binding.apply {

            if (tvUsername.text.isNullOrEmpty()) {
                txtInputUsername showErrorMessage resources.getString(R.string.error_empty_email_id)
                return false
            } else if (!tvUsername.text.toString().isEmail()) {
                txtInputUsername showErrorMessage resources.getString(R.string.error_valid_email)
                return false
            } else if (tvPassword.text.isNullOrEmpty()) {
                txtInputPassword showErrorMessage resources.getString(R.string.error_empty_password)
                return false
            } else if (!tvPassword.text.toString().isValidPassword()) {
                txtInputPassword showErrorMessage resources.getString(R.string.error_valid_password)
                return false
            } else return true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}