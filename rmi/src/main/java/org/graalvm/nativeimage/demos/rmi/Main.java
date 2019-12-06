package org.graalvm.nativeimage.demos.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Random;

public class Main {
   public static void main(String[] args) throws Exception  {
      new Main().startServer("7000");
   }

   public void startServer(String port) throws Exception {
      System.out.println("staring server");
      LocateRegistry.createRegistry(Integer.parseInt(port));
      final String serverUrl = String.format("rmi://localhost:%s/encoder", port);
      Naming.rebind(serverUrl, new Server());
      System.out.println("waiting for connections");
   }
}