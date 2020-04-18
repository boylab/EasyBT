package com.boylab.socket.client.sdk.client;

import java.io.Serializable;
import java.util.UUID;

/**
 * 连接信息服务类
 * Created by xuhao on 2017/5/16.
 */
public final class ConnectionInfo implements Serializable, Cloneable {

    public static final UUID BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    /**
     * IPV4地址
     */
    private String address;
    //String address
    /**
     * 当此IP地址Ping不通时的备用IP
     */
    private ConnectionInfo mBackupInfo;

    public ConnectionInfo(String address) {
        this.address = address;

        //BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    /**
     * 获取传入的IP地址
     *
     * @return ip地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 获取备用的Ip和端口号
     *
     * @return 备用的端口号和IP地址
     */
    public ConnectionInfo getBackupInfo() {
        return mBackupInfo;
    }

    /**
     * 设置备用的IP和端口号,可以不设置
     *
     * @param backupInfo 备用的IP和端口号信息
     */
    public void setBackupInfo(ConnectionInfo backupInfo) {
        mBackupInfo = backupInfo;
    }

    @Override
    public ConnectionInfo clone() {
        ConnectionInfo connectionInfo = new ConnectionInfo(address);
        if (mBackupInfo != null) {
            connectionInfo.setBackupInfo(mBackupInfo.clone());
        }
        return connectionInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof ConnectionInfo)) { return false; }

        ConnectionInfo connectInfo = (ConnectionInfo) o;

        return this.address.equals(connectInfo.address);
    }

    @Override
    public int hashCode() {
        return this.address.hashCode();
    }
}
