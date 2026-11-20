import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter message: ");
            String message = sc.nextLine();

            byte[] sendData = message.getBytes();
            byte[] receiveData = new byte[1024];

            DatagramPacket sendPacket = new DatagramPacket(
                    sendData,
                    sendData.length,
                    IPAddress,
                    9876
            );

            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Server Reply: " + response);

            clientSocket.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}