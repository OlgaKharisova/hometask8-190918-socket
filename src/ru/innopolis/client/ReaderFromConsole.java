package ru.innopolis.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Ожидает ввода сообщения с консоли клиента и отправляет серверу
 */
public class ReaderFromConsole extends Thread{

    private Socket socket;

    public ReaderFromConsole(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (!socket.isClosed()) {
                String message = br.readLine();
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
            }
            br.close();
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
