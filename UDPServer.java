import java.net.*;

public class UDPServer {
    public static void main(String[] args) {

        try {

            DatagramSocket serverSocket = new DatagramSocket(9876);

            byte[] receiveData = new byte[1024];

            System.out.println("Server is waiting...");

            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);

            serverSocket.receive(receivePacket);

            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Message from Client: " + message);

            String reply = "Message received successfully!";

            byte[] sendData = reply.getBytes();

            DatagramPacket sendPacket =
                    new DatagramPacket(
                            sendData,
                            sendData.length,
                            receivePacket.getAddress(),
                            receivePacket.getPort());

            serverSocket.send(sendPacket);

            serverSocket.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}