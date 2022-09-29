package com.mandeep.applaunchtask.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mandeep.applaunchtask.R
import com.mandeep.applaunchtask.data.local.Status
import com.mandeep.applaunchtask.data.local.User
import com.mandeep.applaunchtask.databinding.FragmentUserListBinding
import com.mandeep.applaunchtask.ui.UserAdapter
import com.mandeep.applaunchtask.ui.viewModel.AppViewModel
import com.mandeep.applaunchtask.util.extension.gone
import com.mandeep.applaunchtask.util.extension.onClick
import com.mandeep.applaunchtask.util.extension.showMessage
import com.mandeep.applaunchtask.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private val TAG = UserListFragment::class.java.simpleName
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AppViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        setAdapters()
        setObservers()
        return binding.root
    }

    private fun setAdapters() {
        userAdapter = UserAdapter({
            //On Click
            Log.d(TAG, "setAdapters() called: ${it}")
            findNavController().navigate(R.id.action_userListFragment_to_weatherReportFragment)
        },{
            //On Delete
            viewModel.deleteUser(it)
        })
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getAllUser()
        }
        binding.rcyUserList.adapter = userAdapter
    }

    private fun setObservers() {
        viewModel.user.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = false
            when (it.status) {
                Status.RETRIVE_ALL -> setUserList(it.data as List<User>)
                Status.INSERT -> Log.d(TAG, "setObservers() called")
                Status.UPDATE -> Log.d(TAG, "setObservers() called")
                Status.CLEAR -> Log.d(TAG, "setObservers() called")
                Status.DELETE -> userDeleted()
                Status.RETRIVE -> Log.d(TAG, "setObservers() called")
            }
        }
    }

    private fun userDeleted() {
        binding.root showMessage resources.getString(R.string.msg_user_deleted)
        viewModel.getAllUser()
    }

    private fun setUserList(list: List<User>) {
        Log.d(TAG, "setUserList() called with: list = $list")
        if (list.isNullOrEmpty()) {
            binding.apply {
                tvEmptyList.visible()
                rcyUserList.gone()
            }
        } else {
            binding.apply {
                tvEmptyList.gone()
                rcyUserList.visible()
            }
            userAdapter.submitList(list)
            userAdapter.notifyDataSetChanged()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.getAllUser()
        binding.ivAddNewUser onClick {
            findNavController().navigate(R.id.action_userListFragment_to_addNewUserFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllUser()
    }

}