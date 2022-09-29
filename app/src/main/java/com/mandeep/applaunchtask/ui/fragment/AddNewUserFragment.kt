package com.mandeep.applaunchtask.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mandeep.applaunchtask.R
import com.mandeep.applaunchtask.data.local.Status
import com.mandeep.applaunchtask.data.local.User
import com.mandeep.applaunchtask.data.repository.LocalRepository
import com.mandeep.applaunchtask.databinding.FragmentAddNewUserBinding
import com.mandeep.applaunchtask.ui.viewModel.AppViewModel
import com.mandeep.applaunchtask.util.extension.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class AddNewUserFragment : Fragment() {

    @Inject
    lateinit var localRepository: LocalRepository

    private val viewModel: AppViewModel by viewModels()

    private val TAG = AddNewUserFragment::class.java.simpleName
    private var _binding: FragmentAddNewUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewUserBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setObservers() {
        viewModel.user.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.INSERT -> userAdded()
                else -> clearError()
            }
        }
    }

    private fun userAdded() {
        binding.root showMessage resources.getString(R.string.msg_user_added)
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                findNavController().popBackStack()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        binding.btnSignIn onClick {
            clearError()
            if (validateInputFields()) {
                binding.root.hideKeyboard()
                binding.apply {
                    viewModel.addUser(
                        User(
                            id = 0,
                            firstName = tvFirstName.text.toString(),
                            lastName = tvLastName.text.toString(),
                            emailId = tvEmailId.text.toString()
                        )
                    )

                }
            }
        }

        binding.btnCancel onClick {
            findNavController().popBackStack()
        }

    }

    private fun clearError() {
        binding.apply {
            txtInputFirstName.error = null
            txtInputLastName.error = null
            txtInputEmailId.error = null
        }
    }

    private fun validateInputFields(): Boolean {
        binding.apply {
            if (tvFirstName.text.isNullOrEmpty()) {
                txtInputFirstName showErrorMessage resources.getString(R.string.error_empty_first_name)
                return false
            } else if (!tvFirstName.text.toString().isValidName()) {
                txtInputFirstName showErrorMessage resources.getString(R.string.error_valid_name)
                return false
            } else if (tvLastName.text.isNullOrEmpty()) {
                txtInputLastName showErrorMessage resources.getString(R.string.error_empty_last_name)
                return false
            } else if (!tvLastName.text.toString().isValidName()) {
                txtInputLastName showErrorMessage resources.getString(R.string.error_valid_name)
                return false
            } else if (tvEmailId.text.isNullOrEmpty()) {
                txtInputEmailId showErrorMessage resources.getString(R.string.error_empty_email_id)
                return false
            } else if (!tvEmailId.text.toString().isEmail()) {
                txtInputEmailId showErrorMessage resources.getString(R.string.error_valid_email)
                return false
            } else return true
        }
    }


}