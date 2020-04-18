package com.boylab.socket.server.impl;


import com.boylab.core.iocore.interfaces.IStateSender;
import com.boylab.socket.common.interfaces.common_interfacies.dispatcher.IRegister;
import com.boylab.socket.common.interfaces.common_interfacies.server.IServerActionListener;
import com.boylab.socket.common.interfaces.common_interfacies.server.IServerManager;
import com.boylab.socket.server.action.ServerActionDispatcher;

import java.io.Serializable;

public class AbsServerRegisterProxy implements IRegister<IServerActionListener, IServerManager>, IStateSender {

    protected ServerActionDispatcher mServerActionDispatcher;

    private IServerManager<com.boylab.socket.server.impl.OkServerOptions> mManager;

    protected void init(IServerManager<com.boylab.socket.server.impl.OkServerOptions> serverManager) {
        mManager = serverManager;
        mServerActionDispatcher = new ServerActionDispatcher(mManager);
    }

    @Override
    public IServerManager<com.boylab.socket.server.impl.OkServerOptions> registerReceiver(IServerActionListener socketActionListener) {
        return mServerActionDispatcher.registerReceiver(socketActionListener);
    }

    @Override
    public IServerManager<OkServerOptions> unRegisterReceiver(IServerActionListener socketActionListener) {
        return mServerActionDispatcher.unRegisterReceiver(socketActionListener);
    }

    @Override
    public void sendBroadcast(String action, Serializable serializable) {
        mServerActionDispatcher.sendBroadcast(action, serializable);
    }

    @Override
    public void sendBroadcast(String action) {
        mServerActionDispatcher.sendBroadcast(action);
    }
}
