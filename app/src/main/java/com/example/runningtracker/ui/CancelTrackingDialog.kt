package com.example.runningtracker.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.runningtracker.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CancelTrackingDialog : DialogFragment() {
private var yesListener : (()->Unit)?=null
    fun setYetListener (listener : ()->Unit){
        yesListener=listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return  MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("Cancel the run ?")
            .setMessage("Are you sure that you want to cancel the run ? ")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Yes") { _, _ ->
                yesListener?.let {yes->
                    yes()
                }
            }
            .setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
    }
}