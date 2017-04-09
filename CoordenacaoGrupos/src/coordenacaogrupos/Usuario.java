/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenacaogrupos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Mayara
 */
public class Usuario {
    
    private static MulticastSocket multiSocket;
   
    public static void main(String [] args) throws IOException{
        
               
         try {
		String grupo = "224.0.0.1";
		InetAddress server = InetAddress.getLocalHost();
		multiSocket = new MulticastSocket(3333);
		multiSocket.joinGroup(InetAddress.getByName(grupo));

		while (true) {
                    byte[] buffer = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
			multiSocket.receive(receivePacket);
			String message = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength(), "UTF-8");
                        byte[] sendMsg = new String(ManagementFactory.getRuntimeMXBean().getName()).getBytes();
				
			DatagramPacket sendPacket = new DatagramPacket(sendMsg, sendMsg.length, server, Integer.parseInt(message));
			multiSocket.send(sendPacket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		
    }
}
