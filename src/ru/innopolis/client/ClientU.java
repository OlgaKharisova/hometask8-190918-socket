package ru.innopolis.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientU {
    private static final int CLIENT_PORT1 = 4000;

    public static void main(String[] args) {
        String adress = "127.0.0.1";
        try {
            InetAddress ipadress = InetAddress.getByName(adress);
            Socket socket = new Socket(ipadress, CLIENT_PORT1);

            ReaderFromServer readerFromServer = new ReaderFromServer(socket);
            readerFromServer.start();

            ReaderFromConsole readerFromConsole = new ReaderFromConsole(socket);
            readerFromConsole.start();
/*
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            DataInputStream dataInputStream = new DataInputStream(inputStream);//конвертирование потока для читабельности сообщений
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));//поток для чтения с клавиатуры
            String line = null;

            System.out.println(dataInputStream.readUTF());
            while (!socket.isClosed()) {
                line = bufferedReader.readLine();//ждем пока пользователь введет текст, который отправляется на сервер
                dataOutputStream.writeUTF(line);// отправка текста серверу
                dataOutputStream.flush();
                line = dataInputStream.readUTF();
                System.out.println(line);
            }*/
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//ServerSocket serverSocket1 = new ServerSocket(CLIENT_PORT1);
//Socket socket1 = new Socket("127.0.0.1", ServerU.SERVERU_PORT1);

/*            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()));
            Socket socket = socket1.accept();

            Scanner scanner = new Scanner(System.in);
            String message;
            while ((message = scanner.nextLine()) != "") {
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/