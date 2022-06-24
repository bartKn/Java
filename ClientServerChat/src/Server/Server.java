package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port;
    private ServerSocket serverSocket;

    public Server(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server launched successfully.");
            connectUsers();
        } catch (IOException e) {
            System.out.println("Server cannot be started. " + e.getMessage());
            System.exit(1);
        }
    }

    private void stopServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
                System.out.println("Server stopped.");
            }
        } catch (IOException e) {
            System.out.println("Server cannot be stopped. " + e.getMessage());
        }
    }

    private void connectUsers() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                Thread client = new Thread(serverThread);
                client.start();
            }
        } catch (IOException e) {
            stopServer();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(9999);
        server.startServer();
    }
}
