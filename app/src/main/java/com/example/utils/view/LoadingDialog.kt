package com.example.utils.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.lista_de_filmes_oficial.R
import com.example.lista_de_filmes_oficial.databinding.DialogLoadingBinding

class LoadingDialog: DialogFragment() {

    private lateinit var binding: DialogLoadingBinding

    companion object {
        const val TAG = "dialog_loading"

        @Synchronized
        fun newInstance(): LoadingDialog {
            return LoadingDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_loading, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    fun show(fragmentManager: FragmentManager) {
        val old = fragmentManager.findFragmentByTag(TAG)
        if (old != null && old.isAdded) {
            return
        }
        val ft = fragmentManager.beginTransaction()
        ft.add(this, TAG)
        ft.commit()
    }
}