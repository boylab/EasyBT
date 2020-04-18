package com.boylab.easybt.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.boylab.easybt.R;
import com.boylab.easybt.ReaderProtocol;
import com.boylab.socket.client.impl.client.action.ActionDispatcher;
import com.boylab.socket.client.sdk.OkSocket;
import com.boylab.socket.client.sdk.client.ConnectionInfo;
import com.boylab.socket.client.sdk.client.OkSocketOptions;
import com.boylab.socket.client.sdk.client.action.SocketActionAdapter;
import com.boylab.socket.client.sdk.client.connection.IConnectionManager;
import com.boylab.socket.client.sdk.client.connection.NoneReconnect;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {
    private ConnectionInfo mInfo;
    private IConnectionManager mManager;
    private OkSocketOptions mOkOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BluetoothDevice bluetoothDevice = null;
        bluetoothDevice.getAddress();
    }

    private void initManager(String address) {
        final Handler handler = new Handler();
        mInfo = new ConnectionInfo(address);
        mOkOptions = new OkSocketOptions.Builder()
                .setIOThreadMode(OkSocketOptions.IOThreadMode.SIMPLEX)
                .setReaderProtocol(new ReaderProtocol())
                .setReconnectionManager(new NoneReconnect())
                .setConnectTimeoutSecond(10)
                .setCallbackThreadModeToken(new OkSocketOptions.ThreadModeToken() {
                    @Override
                    public void handleCallbackEvent(ActionDispatcher.ActionRunnable runnable) {
                        handler.post(runnable);
                    }
                })
                .build();
        mManager = OkSocket.open(mInfo).option(mOkOptions);
        mManager.registerReceiver(adapter);
    }

    private SocketActionAdapter adapter = new SocketActionAdapter() {

        @Override
        public void onSocketConnectionSuccess(ConnectionInfo info, String action) {
            // TODO: 2020/4/17 连接成功

            //mManager.send(new HandShakeBean());
        }

        @Override
        public void onSocketDisconnection(ConnectionInfo info, String action, Exception e) {
            if (e != null) {
                Log.i(">>>boylab>>", "异常断开(Disconnected with exception):" + e.getMessage());
            } else {
                Log.i(">>>boylab>>", "正常断开(Disconnect Manually)");
            }

        }

        @Override
        public void onSocketConnectionFailed(ConnectionInfo info, String action, Exception e) {
            // TODO: 2020/4/17 连接失败
        }

        @Override
        public void onSocketReadResponse(ConnectionInfo info, String action, OriginalData data) {
            // TODO: 2020/4/17 读取的数据
            data.getHeadBytes();
            data.getBodyBytes();

            String str = new String(data.getBodyBytes(), Charset.forName("utf-8"));

        }

        @Override
        public void onSocketWriteResponse(ConnectionInfo info, String action, ISendable data) {
            // TODO: 2020/4/17 写出的数据
            //data.parse();

            //String str = new String(data.parse(), Charset.forName("utf-8"));

        }

        @Override
        public void onPulseSend(ConnectionInfo info, IPulseSendable data) {
            // TODO: 2020/4/17 心跳
            //String str = new String(data.parse(), Charset.forName("utf-8"));
        }
    };

    public void connect(String address){
        if (!mManager.isConnect()) {
            initManager(address);
            mManager.connect();
        }
    }

    public void disconnect(){
        if (mManager.isConnect()) {
            mManager.disconnect();
        }
    }
}
