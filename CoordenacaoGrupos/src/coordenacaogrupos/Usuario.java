/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenacaogrupos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Mayara
 */
public class Usuario {
    
    public static void main(String [] args) throws IOException{
    
    byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		MulticastSocket mSocket = new MulticastSocket(3333);
		String group = "224.0.0.1";		
		mSocket.joinGroup(InetAddress.getByName(group));
		
		mSocket.receive(receivePacket);
		System.out.println(new String(receivePacket.getData(), receivePacket.getOffset(),
				receivePacket.getLength()));
		
    }
}
