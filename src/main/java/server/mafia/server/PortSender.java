package server.mafia.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class PortSender extends Thread {

    private static final int thisPort = 9999;

    public static int port = 10000;

    @Override
    public void run() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(thisPort);

            while (true) {

                byte[] message = new byte[50];
                DatagramPacket in = new DatagramPacket(message, message.length);

                datagramSocket.receive(in);

                String str = new String(in.getData(), 0, in.getLength());

                byte[] bytes = String.valueOf(port).getBytes();
                DatagramPacket out = new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());

                datagramSocket.send(out);
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
