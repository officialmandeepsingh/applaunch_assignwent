package com.mandeep.applaunchtask.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mandeep.applaunchtask.R
import com.mandeep.applaunchtask.databinding.FragmentWelcomeBinding
import com.mandeep.applaunchtask.util.extension.onClick

class WelcomeFragment : Fragment() {
    private val TAG = WelcomeFragment::class.java.simpleName
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin onClick {
            WelcomeFragmentDirections.actionWecomeFragmentToLoginFragment()
            findNavController().navigate(R.id.action_WecomeFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}