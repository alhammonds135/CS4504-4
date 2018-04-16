import java.net.*;
import java.util.Scanner;

// Assignment 1
// Author: Alaina Hammonds
// File Name: UDPClient.java
// Purpose: Sends information and receives back converted information using UDP protocol
// Programming Lanuage: Java
// Execution: Compile from command line on client computer using a JDK package, run with four arguments, as seen below
// *THE SERVER PROGRAM MUST BE STARTED BEFORE THIS PROGRAM*

public class UDPClient {

    public static void main(String[] args) {
        // Four command line arguments are expected when running:
        // IP Address, Server Port Number, Client's Port number, and the message to be converted, in that order
        // Error checking for correct number of arguments
        if(args.length != 4) {
            System.out.println("This program requires four command line arguments. Try again.");
        } else {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the host: ");
                InetAddress serverHost = InetAddress.getByName(scanner.nextLine()); //Host to send to

                System.out.print("Enter the port number to send to: ");
                int serverPort = Integer.parseInt(scanner.nextLine()); // Port to send to
                System.out.println("Enter your current port: ");
                int clientPort = Integer.parseInt(scanner.nextLine()); // Current port

                System.out.println("Enter [ProcessNumber].[Num1][Num2]");
                String message = scanner.nextLine(); // Message to send

                //Creates sending and receiving socket
                MyDatagramSocket socket = new MyDatagramSocket(clientPort);

                // Tries to send the packet ten times
                for(int i = 0; i < 10; i++){
                    socket.sendMessage(serverHost, serverPort, message);
                }

                String msg = null;
                // Receives message and prints out
                // This loop waits until the client receives the message back from the server
                while(msg == null) {
                    msg = socket.receiveMessage();
                }

                // Prints the converted message to the console
                System.out.println(msg);

                // Closes the connection
                socket.close();
            } catch (Exception e) {
                System.out.println("There has been an error.");
                e.printStackTrace();
            }
        }
    }
}