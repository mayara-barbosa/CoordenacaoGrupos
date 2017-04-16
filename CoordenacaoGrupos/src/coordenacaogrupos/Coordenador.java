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
    
   	
	public static void main(String[] args) throws IOException, InterruptedException {
	
            MulticastSocket multiSocket = new MulticastSocket();
		byte[] send = new byte[1024];
		byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		String group = "224.0.0.2";
                int port;
                DatagramSocket socket = new DatagramSocket(5555);
                socket.setSoTimeout(1000);
                port = 5555;
                send = Integer.toString(port).getBytes();
                System.out.println(port);
                
		DatagramPacket pacote = new DatagramPacket(send, send.length,InetAddress.getByName(group) , 3333);
		multiSocket.send(pacote);
                int users =0;
                while(true){
                    try{
                        Thread.sleep(5000);
                        socket.receive(receivePacket);
                        String resposta = new String(receivePacket.getData(), receivePacket.getOffset(),
                                    receivePacket.getLength());
                        System.out.println(resposta);
                        users++;
                    }catch(Exception ex){
                        System.out.println("Usuarios conectados: "+users);
                        break;
                    }
                    
				
                }
        }

    
}
