package com.boylab.easybt;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Author pengle on 2020/4/18 16:45
 * Email  pengle609@163.com
 */
public class ScanAdapter extends ArrayAdapter<BluetoothDevice> {

    public ScanAdapter(@NonNull Context context, int resource, @NonNull List<BluetoothDevice> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);

        BluetoothDevice bluetoothDevice = getItem(position);
        String text  = bluetoothDevice.getName() + "\n"+bluetoothDevice.getName();
        textView.setText(text);

        return textView;
    }
}
