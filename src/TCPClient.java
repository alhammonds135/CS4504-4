// Assignment 1
// Author: Alaina Hammonds
// File Name: TCPClient.java
// Purpose: Sends information to another host, then receives the converted information back using TCP protocol
// Programming Lanuage: Java
// Execution: Compile from command line on client computer using a JDK package, run with four arguments, as seen below
// *THE SERVER PROGRAM MUST BE STARTED BEFORE THIS PROGRAM*
import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the host: ");
            InetAddress serverHost = InetAddress.getByName(scanner.nextLine()); //Host to send to

            System.out.print("Enter the server port");
            int serverPort = Integer.parseInt(scanner.nextLine()); // Port to send to
            System.out.println("Enter the client port: ");
            int clientPort = Integer.parseInt(scanner.nextLine()); // Current port

            System.out.println("Enter [ProcessNumber].[Num1][Num2]");
            String message = scanner.nextLine(); // Message to send

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
}
