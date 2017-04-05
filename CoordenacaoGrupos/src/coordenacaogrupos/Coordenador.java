/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenacaogrupos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Mayara
 */
public class Coordenador {
    
    public static void main(String [] args) throws IOException{
		MulticastSocket remSocket = new MulticastSocket();
		byte[] send;
		String message = "hello group";
		send = message.getBytes();
		String group = "224.0.0.1";
		DatagramPacket pacote = new DatagramPacket(send, send.length,InetAddress.getByName(group) , 3333);
		remSocket.send(pacote);
				
	}
}
