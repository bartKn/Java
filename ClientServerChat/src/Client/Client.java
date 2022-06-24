package Client;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Client {

    private String nickname;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private List<String> messageQueue = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();


    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            deleteClient();
        }
    }

    public void setNickname(String nickname) {
        try {
            this.nickname = nickname;
            writer.write("/nick:" + nickname);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return this.nickname;
    }

    public void sendMessage(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                while (socket.isConnected()) {
                    try {
                        message = reader.readLine();
                        messageQueue.add(0, message);
                        printQueue();
                        notifyObservers();
                    } catch (IOException e) {
                        deleteClient();
                    }
                }

            }
        }).start();
    }


    private void printQueue() {
        System.out.println();
        System.out.println("---------");
        for (String str: messageQueue) {
            System.out.println(str);
        }
    }

    public String getFromQueue(int index) {
        if (index + 1 > messageQueue.size()) {
            return "";
        } else {
            /*if (messageQueue.size() == 9) {
                messageQueue.remove(8);
            }*/
            return messageQueue.get(index);
        }
    }


    private void deleteClient() {
        try {
            if (this.reader != null) {
                this.reader.close();
            }

            if (this.writer != null) {
                this.writer.close();
            }

            if (this.socket != null) {
                this.socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("localhost", 9999);

        Client client = new Client(socket);
        client.getMessage();

    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update();
        }
    }
}
