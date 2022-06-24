package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread implements Runnable {

    public static List<ServerThread> connections = new ArrayList<>();
    public static int usersNumber = 1;

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String username;

    public ServerThread(Socket socket) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = "client" + usersNumber;
            usersNumber += 1;
            connections.add(this);
            sendMessage("SERVER: " + username + " connected.");
            System.out.println(username + " connected.");
        } catch (IOException e) {
            closeConnection();
        }
    }

    @Override
    public void run() {
        String message;
        String newUsername;

        while (socket.isConnected()) {
            try {
                message = reader.readLine();
                System.out.println(message);
                if (message.startsWith("/nick:")) {
                    newUsername = message.substring(6);
                    sendMessage("SERVER: " + username + " has changed nickname to " + newUsername);
                    this.username = newUsername;
                } else {
                    sendMessage(username + ": " + message);
                }
            } catch (IOException | NullPointerException e) {
                closeConnection();
                break;
            }
        }
    }

    public void sendMessage(String message) {
        for (ServerThread client: connections) {
            try {
                client.writer.write(message);
                client.writer.newLine();
                client.writer.flush();
            } catch (IOException e) {
                closeConnection();
            }
        }
    }

    private void removeFromConnectionsList() {
        connections.remove(this);
        sendMessage(username + " has disconnected.");
    }

    private void closeConnection() {
        removeFromConnectionsList();
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
}
