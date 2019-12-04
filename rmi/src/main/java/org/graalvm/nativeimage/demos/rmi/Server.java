package org.graalvm.nativeimage.demos.rmi;

import java.nio.charset.StandardCharsets;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;

public class Server extends UnicastRemoteObject {

    private final Base64.Encoder encoder = Base64.getUrlEncoder();

    protected Server() throws RemoteException {
        super();
    }

    public String encode(String message) {
        final byte[] encoded = encoder.encode(message.getBytes(StandardCharsets.UTF_8));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
