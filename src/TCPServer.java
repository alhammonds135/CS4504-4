// Assignment 1
// Author: Alaina Hammonds
// File Name: TCPServer.java
// Purpose: Receives and converts information, then sends it back using TCP protocol
// Programming Lanuage: Java
// Execution: Compile from command line on sever computer using a JDK package, run with four arguments, as seen below.
//  *THIS PROGRAM MUST BE STARTED BEFORE THE CLIENT PROGRAM*

import java.net.InetAddress;

public class TCPServer {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("This program requires three command line arguments. Try again.");
        } else {
            try {
                InetAddress clientHost = InetAddress.getByName(args[0]); // Host to send back to
                int clientPort = Integer.parseInt(args[1]); // Port to send back to
                int serverPort = Integer.parseInt(args[2]); // Current port

                // Creats new socket
                MyDatagramSocket socket = new MyDatagramSocket(serverPort);

                // Connects to client
                socket.connect(clientHost, clientPort);

                // Receives message from client and prints out
                String message = socket.receiveMessage();
                System.out.println(message);

                // Converts message to uppercase
                String newMessage = message.toUpperCase();

                // Sends back converted message;
                socket.sendMessage(clientHost, clientPort, newMessage);

                //Disconnects and closes socket
                socket.disconnect();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}