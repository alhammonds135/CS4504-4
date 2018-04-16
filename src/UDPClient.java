import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

// Assignment 1
// Author: Alaina Hammonds
// File Name: UDPClient.java
// Purpose: Sends information and receives back converted information using UDP protocol
// Programming Lanuage: Java
// Execution: Compile from command line on client computer using a JDK package, run with four arguments, as seen below
// *THE SERVER PROGRAM MUST BE STARTED BEFORE THIS PROGRAM*

public class UDPClient implements MyRunnable {
    private DatagramSocket socket;
    int serverPort;
    int clientPort;
    InetAddress serverHost;

    public UDPClient() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the host: "); //Host to send to
            try {
                serverHost = InetAddress.getByName(scanner.nextLine());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            System.out.print("Enter the server port: ");
            serverPort = Integer.parseInt(scanner.nextLine()); // Port to send to

            System.out.print("Enter the client port");
            clientPort = Integer.parseInt(scanner.nextLine());

            socket = new DatagramSocket(clientPort);

        } catch (Exception e) {
            System.out.println("There has been an error.");
            e.printStackTrace();
        }
    }

    @Override
    public void run(int counter) {
        String message = new NumPairFormatter(counter).toString();
        byte[] sendbuff = message.getBytes();
        DatagramPacket p = new DatagramPacket(sendbuff, sendbuff.length, serverHost, serverPort);
        try {
            socket.send(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buff = new byte[20];
        DatagramPacket datagram = new DatagramPacket(buff, 20);
        try {
            socket.receive(datagram);
            System.out.println("\t" + message + " ->" + new String(buff));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
