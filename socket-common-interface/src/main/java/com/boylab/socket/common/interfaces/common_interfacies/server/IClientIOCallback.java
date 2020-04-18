package com.boylab.socket.common.interfaces.common_interfacies.server;


import com.boylab.core.iocore.interfaces.ISendable;
import com.boylab.core.pojo.OriginalData;

public interface IClientIOCallback {

    void onClientRead(OriginalData originalData, IClient client, com.boylab.socket.common.interfaces.common_interfacies.server.IClientPool<IClient, String> clientPool);

    void onClientWrite(ISendable sendable, IClient client, IClientPool<IClient, String> clientPool);

}
