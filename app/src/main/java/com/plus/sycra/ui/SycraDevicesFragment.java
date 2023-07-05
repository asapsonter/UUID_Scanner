package com.plus.sycra.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.plus.sycra.databinding.FragmentSycraDevicesBinding;

import java.util.List;

public class SycraDevicesFragment extends Fragment {

    private SycraDevicesViewModel viewModel;
    private FragmentSycraDevicesBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSycraDevicesBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the view model using the ViewModelProvider
        viewModel = new ViewModelProvider(this).get(SycraDevicesViewModel.class);
        // Observe the live data of devices from the view model
        viewModel.getDevices().observe(getViewLifecycleOwner(),
                new Observer<List<SycraDevice>>(){

                    @Override
                    public void onChanged(List<SycraDevice> devices) {
                        // Create an adapter with the devices list and set it to the recycler view
                        SycraDeviceAdapter adapter = new SycraDeviceAdapter(devices);
                        binding.devicesRecyclerView.setAdapter(adapter);

                    }
                });
    }

    }