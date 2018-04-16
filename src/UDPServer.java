import java.net.*;

// Author: Alaina Hammonds

public class UDPServer {
    public static void main(String[] args) {
        // Three command line arguments are expected when running:
        // IP Address, Server Port Number, and Client's Port number
        // Error checking for correct number of arguments
        if (args.length != 3) {
            System.out.println("This program requires three command line arguments. Try again.");
        } else {
            try {
                InetAddress clientHost = InetAddress.getByName(args[0]); //Host to send to
                int serverPort = Integer.parseInt(args[1]); // Port to send to
                int clientPort = Integer.parseInt(args[2]); // Current port

                // Creates socket to receive message from and send message back
                DatagramSocket socket = new DatagramSocket(serverPort);

                String message;

                while(true) {
                    // Receives message from client and prints to command line
                    byte[] buff = new byte[20];
                    DatagramPacket packet = new DatagramPacket(buff, 20);

                    socket.receive(packet);

                    message = new String(buff);

                    String[] splitStr = message.split("\\.");
                    int processNum = Integer.parseInt(splitStr[0]);
                    String[] splitAgain = splitStr[1].split(",");

                    int num1 = Integer.parseInt(splitAgain[0]);

                    int num2;

                    try {
                        num2 = Integer.parseInt(splitAgain[1].substring(0, 2));
                    } catch (NumberFormatException f) {
                        num2 = Integer.parseInt(splitAgain[1].substring(0, 1));
                    }

                    String newMessage = processNum + "." + Integer.toString(num1 + num2);

                    byte[] b = newMessage.getBytes();

                    DatagramPacket packet1 = new DatagramPacket(b, b.length, clientHost, clientPort);
                    socket.send(packet1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}