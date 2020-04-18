package com.boylab.socket.client.sdk.client;


import android.bluetooth.BluetoothSocket;

public abstract class OkSocketFactory {

    public abstract BluetoothSocket createSocket(ConnectionInfo info, OkSocketOptions options) throws Exception;

}
