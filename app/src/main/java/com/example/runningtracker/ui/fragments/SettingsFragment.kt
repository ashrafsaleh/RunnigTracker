package com.example.runningtracker.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.runningtracker.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject
@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFieldsFromSharedPref()
        btnApplyChanges.setOnClickListener {
            val success = applyChangesSharedPref()
            if(success) {
                Snackbar.make(view, "Saved changes", Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(view, "Please fill out all the fields", Snackbar.LENGTH_LONG).show()
            }
        }
    }
    private fun loadFieldsFromSharedPref() {
        val name = sharedPref.getString("KEY_NAME", "")
        val weight = sharedPref.getFloat("KEY_WEIGHT", 80f)
        etName.setText(name)
        etWeight.setText(weight.toString())
    }
    private fun applyChangesSharedPref():Boolean{
    val name = etName.text.toString()
    val weight = etWeight.text.toString()
    if (name.isEmpty()||weight.isEmpty()){
        return false
    }
        sharedPref.edit()
            .putString("KEY_NAME",name)
            .putFloat("KEY_WEIGHT",weight.toFloat())
            .putBoolean("KEY_FIRST_TIME_TOGGLE",false)
            .apply()

        val toolbarText = "Lets go, $name !"
        requireActivity().tvToolbarTitle.text=toolbarText
        return true
}
}