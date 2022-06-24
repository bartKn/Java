package Client.View;

import Client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import Client.Observer;

public class ClientForm extends Observer {
    private JPanel mainPanel;
    private JTextField messageText;
    private JButton sendButton;
    private JTextField nickText;
    private JButton setButton;
    private JTextField message7;
    private JTextField message6;
    private JTextField message5;
    private JTextField message4;
    private JTextField message3;
    private JTextField message2;
    private JTextField message1;
    private JTextField message0;
    private JLabel nickname;

    private Client client;

    private static ClientForm clientForm;

    public ClientForm(Client client) {

        this.client = client;
        this.client.attach(this);
        this.client.getMessage();

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageText.getText();
                if (message.length() > 0) {
                    client.sendMessage(message);
                }
                messageText.setText("");
                printTextFields();
            }
        });

        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = nickText.getText();
                if (nickname.length() > 0 && !nickname.equals(client.getNickname())) {
                    client.setNickname(nickname);
                }
            }
        });
    }

    public void printTextFields() {
        message0.setText(client.getFromQueue(0));
        message1.setText(client.getFromQueue(1));
        message2.setText(client.getFromQueue(2));
        message3.setText(client.getFromQueue(3));
        message4.setText(client.getFromQueue(4));
        message5.setText(client.getFromQueue(5));
        message6.setText(client.getFromQueue(6));
        message7.setText(client.getFromQueue(7));
    }

    @Override
    public void update() {
        printTextFields();
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Chat");
        try {
            Socket socket = new Socket("localhost", 9999);
            Client client = new Client(socket);
            clientForm = new ClientForm(client);
            mainFrame.setContentPane(clientForm.mainPanel);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.pack();
            mainFrame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


    }
}
