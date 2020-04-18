package com.boylab.easybt;

import com.boylab.core.protocol.IReaderProtocol;

import java.nio.ByteOrder;

/**
 * Author pengle on 2020/4/17 12:49
 * Email  pengle609@163.com
 */
public class ReaderProtocol implements IReaderProtocol {
    @Override
    public int getHeaderLength() {
        return 1;
    }

    @Override
    public int getBodyLength(byte[] header, ByteOrder byteOrder) {
        if (header[0] == '='){
            return 8;
        }
        return 0;
    }
}
