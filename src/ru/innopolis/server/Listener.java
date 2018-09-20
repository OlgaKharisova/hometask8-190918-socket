package ru.innopolis.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Listener extends Thread {

    private ServerSocket serverSocket;
    private final ArrayList<Socket> sockets = new ArrayList<>();

    public Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        start();
    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()) {
            try {
                Socket newClient = serverSocket.accept();
                DataInputStream dis = new DataInputStream(newClient.getInputStream());
                DataOutputStream dos = new DataOutputStream(newClient.getOutputStream());
                dos.writeUTF("Сервер: Представьтесь:");
                String name = dis.readUTF();
                dos.writeUTF("Сервер: Добро пожаловать в чат, " + name + "!");
                System.out.println("Подключен клиент " + name);
                synchronized (sockets) {
                    sockets.add(newClient);
                }
                ReaderStream readerStream = new ReaderStream(newClient, this, name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized ArrayList<Socket> getSockets() {
        return sockets;
    }
}
