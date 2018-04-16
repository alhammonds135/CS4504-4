import java.net.*;

// Assignment 1
// Author: Alaina Hammonds
// File Name: UDPServer.java
// Purpose: Receives and converts information, then sends it back using UDP protocol
// Programming Lanuage: Java
// Execution: Compile from command line on server computer using a JDK package, run with four arguments, as seen below
//  *THIS PROGRAM MUST BE STARTED BEFORE THE CLIENT PROGRAM*

public class UDPServer {
    public static void main(String[] args) {
        // Three command line arguments are expected when running:
        // IP Address, Server Port Number, and Client's Port number
        // Error checking for correct number of arguments
        if (args.length != 3) {
            System.out.println("This program requires three command line arguments. Try again.");
        } else {
            try {
                InetAddress clientHost = InetAddress.getByName(args[0]); // Host to send back to
                int clientPort = Integer.parseInt(args[1]); // Port to send back to
                int serverPort = Integer.parseInt(args[2]); // Current port

                // Creates socket to receive message from and send message back
                MyDatagramSocket socket = new MyDatagramSocket(serverPort);

                String message;

                // Receives message from client and prints to command line
                message = socket.receiveMessage();
                System.out.println("Message received: " + message);

                // Converts message to uppercase
                String newMessage = message.toUpperCase();

                // Sends new uppercase message
                socket.sendMessage(clientHost, clientPort, newMessage);

                // Closes socket
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
