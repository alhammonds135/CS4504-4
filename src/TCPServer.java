// Assignment 1
// Author: Alaina Hammonds
// File Name: TCPServer.java
// Purpose: Receives and converts information, then sends it back using TCP protocol
// Programming Lanuage: Java
// Execution: Compile from command line on sever computer using a JDK package, run with four arguments, as seen below.
//  *THIS PROGRAM MUST BE STARTED BEFORE THE CLIENT PROGRAM*

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class TCPServer {
    public static void main(String[] args) {
        try {
            InetAddress clientHost = InetAddress.getByName(args[1]); //Host to send to
            int serverPort = Integer.parseInt(args[2]); // Port to send to
            int clientPort = Integer.parseInt(args[3]); // Current port

            // Creates new socket
            MyDatagramSocket socket = new MyDatagramSocket(serverPort);

            // Connects to client
            socket.connect(clientHost, clientPort);

            String message;

            message = socket.receiveMessage();

            String [] splitStr = message.split(".");
            int processNum = Integer.parseInt(splitStr[0]);
            String [] splitAgain = splitStr[1].split(",");

            int num1 = Integer.parseInt(splitAgain[0]);
            int num2 = Integer.parseInt(splitAgain[1]);

            // Converts message to uppercase
            String newMessage = processNum + "." + Integer.toString(num1 + num2);

            // Sends back converted message;
            socket.sendMessage(clientHost, clientPort, newMessage);

            //Disconnects and closes socket
            socket.disconnect();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}