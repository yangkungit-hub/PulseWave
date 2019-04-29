package com.db.app.service;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;


public class BLEService {
    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothAdapter.LeScanCallback mLeScanCallback;
    private boolean mScanning = false;

    private static final int REQUEST_ENABLE_BT = 1;


    public BLEService(Activity activity,
                      BluetoothAdapter.LeScanCallback mLeScanCallback) {
        
        mBluetoothManager = (BluetoothManager) activity.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();

        // 检查设备是否支持蓝牙
        if (mBluetoothAdapter == null) {
            activity.finish();
            return;
        }

        // 确保设备上已启用蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            if (!mBluetoothAdapter.isEnabled()) {
                // 如果蓝牙当前未启用，则激发一个显示对话框的意图，要求用户授予启用它的权限
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }

        this.mLeScanCallback = mLeScanCallback; // 自定义扫描回调函数
    }

    public void scanDeviceEnable(final boolean enable) {
        if (enable) { // 启动扫描
            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else { // 停止扫描
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }
}
