import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

// Assignment 1
// Author: Alaina Hammonds
// File Name: UDPServer.java
// Purpose: Subclass of DatagramSocket used to send and receive messages, used by TCP and UDP programs

public class MyDatagramSocket extends DatagramSocket {
    static final int MAX_LEN = 10;
    MyDatagramSocket(int portNo) throws SocketException {
        super(portNo);
    }

    public void sendMessage (InetAddress receiverHost, int receiverPort, String message) {
        byte[] sendBuffer = message.getBytes();
        DatagramPacket datagram = new DatagramPacket(sendBuffer, sendBuffer.length, receiverHost, receiverPort);
        try {
            this.send(datagram);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() throws IOException {
        byte[] receiveBuffer = new byte[MAX_LEN];
        DatagramPacket datagram = new DatagramPacket(receiveBuffer, MAX_LEN);
        this.receive(datagram);
        return new String(receiveBuffer);
    }
}
