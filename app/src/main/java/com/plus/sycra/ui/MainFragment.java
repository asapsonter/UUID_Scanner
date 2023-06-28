package com.plus.sycra.ui;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.plus.sycra.R;
import com.plus.sycra.databinding.FragmentMainBinding;

import no.nordicsemi.android.support.v18.scanner.ScanCallback;
import no.nordicsemi.android.support.v18.scanner.ScanResult;

public class MainFragment extends Fragment {
    //private MainViewModel mViewModel;
    private FragmentMainBinding binding;
    private BluetoothLeScanner scanner;
    private ScanCallback scanCallback;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.
                inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Access views using binding object
        binding.discover.setText(R.string.discover_txt);
        binding.grouping.setText(R.string.grouping_txt);

        //Toast message for discover button
        binding.discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and show a toast message for discover button
                Toast.makeText(requireContext(), "You clicked discover",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Toast message for grouping button
        binding.grouping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and show a toast message for grouping button
                Toast.makeText(requireContext(), "You clicked grouping",
                        Toast.LENGTH_SHORT).show();


                // Scan for BLE devices
                scanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
                scanCallback = new ScanCallback() {
                    @Override
                    public void onScanResult(int callbackType, @NonNull ScanResult result) {
                        super.onScanResult(callbackType, result);
                        // Display BLE devices in another fragment using RecyclerView
                    }
                };
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                scanner.startScan(scanCallback);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}