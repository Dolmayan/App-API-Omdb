package com.example.utils.base

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.example.utils.view.LoadingDialog

class BaseObserver(
    private val baseViewModel: BaseViewModel,
    private val fragmentManager: FragmentManager?
) {

    fun observeChanges(owner: LifecycleOwner) {
        baseViewModel.isLoading.observe(
            owner, { shouldLoading ->
                if (shouldLoading) {
                    showLoading()
                } else {
                    hideLoading()
                }
            })
    }

    private fun showLoading() {
        fragmentManager?.let { LoadingDialog.newInstance().show(it) }
    }

    private fun hideLoading() {
        (fragmentManager?.findFragmentByTag(LoadingDialog.TAG) as DialogFragment?)?.dismiss()
    }
}