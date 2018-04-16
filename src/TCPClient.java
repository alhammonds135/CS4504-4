// Assignment 1
// Author: Alaina Hammonds
// File Name: TCPClient.java
// Purpose: Sends information to another host, then receives the converted information back using TCP protocol
// Programming Lanuage: Java
// Execution: Compile from command line on client computer using a JDK package, run with four arguments, as seen below
// *THE SERVER PROGRAM MUST BE STARTED BEFORE THIS PROGRAM*
import java.io.IOException;
import java.net.InetAddress;
public class TCPClient implements MyRunnable {
    public static void main(String[] args) {
        try {
            InetAddress serverHost = InetAddress.getByName(args[1]); //Host to send to
            int serverPort = Integer.parseInt(args[2]); // Port to send to
            int clientPort = Integer.parseInt(args[3]); // Current port
            String message = args[3]; // Message to send

            // Creates socket
            MyDatagramSocket socket = new MyDatagramSocket(clientPort);

            // Connect to server
            socket.connect(serverHost, serverPort);

            // Send message
            socket.sendMessage(serverHost, serverPort, message);

            // Receives message and prints out
            System.out.println(socket.receiveMessage());

            // Disconnects and closes socket
            socket.disconnect();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(int counter) {

    }
}