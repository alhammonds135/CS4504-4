import java.net.*;
import java.util.Scanner;

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
                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter the host: ");
                InetAddress clientHost = InetAddress.getByName(scanner.nextLine()); // Host to send back to

                System.out.print("Enter the client port: ");
                int clientPort = Integer.parseInt(scanner.nextLine()); // Port to send back to

                System.out.print("Enter the server port: ");
                int serverPort = Integer.parseInt(scanner.nextLine()); // Current port

                // Creates socket to receive message from and send message back
                MyDatagramSocket socket = new MyDatagramSocket(serverPort);

                String message;

                // Receives message from client and prints to command line
                message = socket.receiveMessage();

                String [] splitStr = message.split(".");
                int processNum = Integer.parseInt(splitStr[0]);
                String [] splitAgain = splitStr[1].split(",");

                int num1 = Integer.parseInt(splitAgain[0]);
                int num2 = Integer.parseInt(splitAgain[1]);

                // Converts message to uppercase
                String newMessage = processNum + "." + Integer.toString(num1 + num2);

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