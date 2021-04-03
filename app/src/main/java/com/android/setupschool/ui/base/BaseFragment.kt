package com.android.setupschool.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseFragment<T: ViewDataBinding>(@LayoutRes private val layoutId: Int) : Fragment() {
    private var _binding : T? = null
    val binding get() = _binding ?: throw IllegalAccessException("binding used outside view lifecycle")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setToolbar(toolbar: Toolbar){
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
    }
}