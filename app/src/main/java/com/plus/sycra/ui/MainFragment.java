package com.plus.sycra.ui;

import  android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.plus.sycra.R;
import com.plus.sycra.databinding.FragmentMainBinding;

import java.util.List;
public class MainFragment extends Fragment {
    //private MainViewModel mViewModel;
    private FragmentMainBinding binding;

    private static final int REQUEST_CODE_BLE_PERMISSIONS = 1;

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
        // Request the permission when needed

        //***** on click method that handles discover button ***/
        binding.discover.setOnClickListener(view1 -> {
            // Create and show a toast message for discover button
            Toast.makeText(requireContext(), "You clicked discover",
                    Toast.LENGTH_SHORT).show();
            // Scan for BLE devices
           // scanForBleDevices();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                requestBlePermissions();
            }
            // Navigate to SycraDevicesFragment using the action ID
            NavHostFragment.findNavController(MainFragment.this)
                    .navigate(R.id.action_mainFragment_to_sycraDevicesFragment);
            //action_mainFragment_to_sycraDevicesFragment
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.S)
    private void requestBlePermissions() {
        String[] permissions = new String[]{
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT
        };
        requestPermissions(permissions, REQUEST_CODE_BLE_PERMISSIONS);
    }
    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_BLE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // Permissions granted, scan for BLE devices
                scanForBleDevices();
            } else {
                // Permissions denied, show a toast message or a dialog to explain why they are needed
                Toast.makeText(requireContext(), "BLE permissions are required to scan and connect to devices", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    private void scanForBleDevices() {
        BluetoothLeScanner scanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
        // Display BLE devices in another fragment using RecyclerView
        // permission to check bluetooth connection
        // Print the device name and address to the logcat

        //* create a reference of scanner class
        ScanCallback scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
                // Display BLE devices in another fragment using RecyclerView

                Bundle bundle = new Bundle();
                // permission to check bluetooth connection
                if (ActivityCompat.checkSelfPermission(requireContext(),
                        Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.
                        PERMISSION_GRANTED) {
                    return;
                }
                bundle.putString("device_name", result.getDevice().getName());
                bundle.putString("device_address", result.getDevice().getAddress());

                // Print the device name and address to the logcat
                Log.d("BLE_SCAN", "Scanned device: " + result.getDevice().getName()
                        + " (" + result.getDevice().getAddress() + ")");
            }
            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
            }
            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
            }
        };
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            requestPermissions(new String[] {Manifest.permission.BLUETOOTH_SCAN}, REQUEST_CODE_BLE_PERMISSIONS);
            return;
        }
        scanner.startScan(scanCallback);
    }
    // Register a callback for requesting and handling the permission

    // Request the permission when needed

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }

}