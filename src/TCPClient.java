// Assignment 1
// Author: Alaina Hammonds
// File Name: TCPClient.java
// Purpose: Sends information to another host, then receives the converted information back using TCP protocol
// Programming Lanuage: Java
// Execution: Compile from command line on client computer using a JDK package, run with four arguments, as seen below
// *THE SERVER PROGRAM MUST BE STARTED BEFORE THIS PROGRAM*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

// Author: Eshin Griffith

public class TCPClient implements MyRunnable{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public TCPClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the host: ");
        InetAddress serverHost = null; //Host to send to
        try {
            serverHost = InetAddress.getByName(scanner.nextLine());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.print("Enter the server port: ");
        int serverPort = Integer.parseInt(scanner.nextLine()); // Port to send to

//         Connect to server
        try {
            socket = new Socket(serverHost,serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(int counter) {
        String message = new NumPairFormatter(counter).toString();
        out.println(message);
        try {
            System.out.println("\t" + message + " ->" + in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}