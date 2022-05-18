package com.example.starwarsapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.starwarsapp.databinding.ActivityPeopleBinding
import com.example.starwarsapp.presentation.viewmodel.BaseViewModel


abstract class BaseActivity<B : ViewDataBinding, V : BaseViewModel>(
    private val resId: Int,
) : AppCompatActivity() {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, resId)
        binding.lifecycleOwner = this
    }

    abstract fun getVM(): V?
}