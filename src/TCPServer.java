// Assignment 1
// Author: Alaina Hammonds
// File Name: TCPServer.java
// Purpose: Receives and converts information, then sends it back using TCP protocol
// Programming Lanuage: Java
// Execution: Compile from command line on sever computer using a JDK package, run with four arguments, as seen below.
//  *THIS PROGRAM MUST BE STARTED BEFORE THE CLIENT PROGRAM*

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {
    public static void main(String[] args) {
        try {
            InetAddress clientHost = InetAddress.getByName(args[0]); //Host to send to
            int serverPort = Integer.parseInt(args[1]); // Port to send to

            // Creates new socket
            ServerSocket serverSocket = new ServerSocket(serverPort);

            Socket socket = serverSocket.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String message = in.readLine();

                String[] splitStr = message.split("\\.");
                int processNum = Integer.parseInt(splitStr[0]);
                String[] splitAgain = splitStr[1].split(",");

                int num1 = Integer.parseInt(splitAgain[0]);
                int num2 = Integer.parseInt(splitAgain[1]);

                // Converts message to uppercase
                String newMessage = processNum + "." + Integer.toString(num1 + num2);

                // Sends back converted message;
                out.println(newMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}