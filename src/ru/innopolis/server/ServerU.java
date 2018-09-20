package ru.innopolis.server;

import java.io.*;
import java.net.ServerSocket;

public class ServerU extends Thread {

    public static final int SERVERU_PORT1 = 4000;
    //ServerU serverU = new ServerU();
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket1 = new ServerSocket(SERVERU_PORT1);
            new Listener(serverSocket1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
    }
}
