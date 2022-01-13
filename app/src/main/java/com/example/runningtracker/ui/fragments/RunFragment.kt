package com.example.runningtracker.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.runningtracker.R
import com.example.runningtracker.adapters.RunAdapter
import com.example.runningtracker.others.SortType
import com.example.runningtracker.others.TrackingUtility
import com.example.runningtracker.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_run.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.security.Permission
import java.util.jar.Manifest

@AndroidEntryPoint
class RunFragment : Fragment(R.layout.fragment_run),EasyPermissions.PermissionCallbacks {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var runAdapter: RunAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
        setupRecyclerView()

        when(viewModel.sortType){
            SortType.DATE->spFilter.setSelection(0)
            SortType.TIME->spFilter.setSelection(1)
            SortType.DISTANCE->spFilter.setSelection(2)
            SortType.AVG_SPEED->spFilter.setSelection(3)
            SortType.CALORIES_BURNED->spFilter.setSelection(4)
        }

        spFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                when(pos){
                    0-> viewModel.sortRuns(SortType.DATE)
                    1-> viewModel.sortRuns(SortType.TIME)
                    2-> viewModel.sortRuns(SortType.DISTANCE)
                    3-> viewModel.sortRuns(SortType.AVG_SPEED)
                    4-> viewModel.sortRuns(SortType.CALORIES_BURNED)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        viewModel.runs.observe(viewLifecycleOwner, Observer {
            runAdapter.submitList(it)
        })
        fab.setOnClickListener{
            findNavController().navigate(R.id.action_runFragment_to_trackingFragment)
        }
    }
    private fun setupRecyclerView()=rvRuns.apply{
        runAdapter = RunAdapter()
        adapter = runAdapter
        layoutManager=LinearLayoutManager(requireContext())
    }
    private fun requestPermissions(){
        if (TrackingUtility.locationPermission(requireContext())){
            return
        }
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.Q){
            EasyPermissions.requestPermissions(this,
            "you have to enable location permissions to continue.",0,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        else{
            EasyPermissions.requestPermissions(this,
                "you have to enable location permissions to continue.",0,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }
        else{
            requestPermissions()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }

}