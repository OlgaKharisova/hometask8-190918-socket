package ru.innopolis.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Ожидает сообщеия от сервера и записывает сообщения в консоль
 */
public class ReaderFromServer extends Thread {

    private Socket socket;

    public ReaderFromServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (!socket.isClosed()) {
                String message = dis.readUTF();
                System.out.println(message);
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
