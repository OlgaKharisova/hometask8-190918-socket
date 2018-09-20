package ru.innopolis.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Ожидает сообщений от клиента. После прихода сообщений рассылает его всем подключенным клиентам
 */
public class ReaderStream extends Thread {

    private Socket socket;
    private Listener listener;
    private String clientName;

    public ReaderStream(Socket newClient, Listener listener, String name) {
        this.socket = newClient;
        this.listener = listener;
        this.clientName = name;
        start();
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (!socket.isClosed()) {
                String message = dis.readUTF();
                if (message.equalsIgnoreCase("quit")) {
                    sendToAll(clientName + " покинул чат");
                    dis.close();
                    socket.close();
                    synchronized (listener.getSockets()) {
                        listener.getSockets().remove(socket);
                    }
                    break;
                }
                String broadcast = clientName + ": " + message;//широкое вещание,т.е. сообщение для всех
                sendToAll(broadcast);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendToAll(String message) throws IOException {
        ArrayList<Socket> sockets = listener.getSockets();
        for (Socket socket : sockets) {
            new DataOutputStream(socket.getOutputStream()).writeUTF(message);
        }
    }
}