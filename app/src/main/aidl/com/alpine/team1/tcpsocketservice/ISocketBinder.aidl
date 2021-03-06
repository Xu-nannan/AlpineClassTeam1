// ISocketBinder.aidl
package com.alpine.team1.tcpsocketservice;

import com.alpine.team1.tcpsocketservice.IOnSocketReceivedListener;

// Declare any non-default types here with import statements

interface ISocketBinder {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    const String SERVICE_NAME = "com.alpine.team1.tcpsocketservice.SocketService";

    void setUDPEnabled(boolean enabled);
    void setTCPEnabled(boolean enabled);
    void setTCPServerEnabled(boolean enabled);
    boolean isUDPEnabled();
    boolean isTCPEnabled();
    boolean isTCPServerEnabled();

    void setLocalPort(int Port);
    int getLocalPortPort();
    void setRemoteIP(String ip);
    String getRemoteIP();
    void setRemotePort(int port);
    int getRemotePort();

    void sendText(String text);
    void connect(String remoteIp, int remotePort);
    void disconnect(String remoteIp, int remotePort);

    void registerListener(IOnSocketReceivedListener listener);
    void unregisterListener(IOnSocketReceivedListener listener);
}
