/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenacaogrupos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mayara
 */
public class Coordenador {
    
   	public static DatagramSocket unicastSocket;
        private static DatagramPacket packet;
	public static ArrayList<String> msg = new ArrayList<>();
	private static byte[] buffer;
	

	public static void main(String[] args) throws InterruptedException {
		
            try {
                    unicastSocket = new DatagramSocket();
                     InetAddress grupo = InetAddress.getByName("224.0.0.1");

                    while (true) {
                    	buffer = new String(Integer.toString(unicastSocket.getLocalPort())).getBytes();
			packet = new DatagramPacket(buffer, buffer.length, grupo, 3333);

			Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
                            try {
				unicastSocket.send(packet);
				buffer = new byte[1024];
				packet = new DatagramPacket(buffer, buffer.length);
				unicastSocket.receive(packet);

				String response = new String(packet.getData(), packet.getOffset(), packet.getLength(), "UTF-8");
						msg.add(response);
				} catch (Exception e) {
                                    e.printStackTrace();
				}
			}
				});
				
			t.start();
			t.join(10000);
				
								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
				
	}

    
}
