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
        
               
        int port;
        InetAddress ip;
	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];
	BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        DatagramPacket sendPacket; new DatagramPacket(sendData,sendData.length);
	MulticastSocket mSocket = new MulticastSocket(3333);
	String group = "224.0.0.2";		
	mSocket.joinGroup(InetAddress.getByName(group));
	mSocket.receive(receivePacket);
                
            
        String resposta = new String(receivePacket.getData(), receivePacket.getOffset(),
				     receivePacket.getLength());
        port = Integer.parseInt(resposta);
                
        String id = ManagementFactory.getRuntimeMXBean().getName();
        sendData = id.getBytes();
	DatagramPacket pacote = new DatagramPacket(sendData, sendData.length,receivePacket.getAddress() , port);
        mSocket.send(pacote);

        
		
    }
}
