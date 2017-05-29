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
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

/**
 *
 * @author Mayara
 */
public class Usuario extends Thread{
    
               
        int port;
        InetAddress ip;
	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];
	BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	DatagramPacket receivePacket;
        DatagramPacket sendPacket ;
	MulticastSocket mSocket;
        DatagramSocket socket;
	String group = "224.0.0.2";
        String mensagem, id;
	int coordenadorPort;
                
        @Override
        public void run(){
             try{
            socket.setSoTimeout(5000);
               while(true){
                    System.out.println("");
                    Random random = new Random();
                    int rand = random.nextInt(1000);
                    System.out.println("");
                    Thread.sleep(rand);
                    mensagem = "AYA";
                    sendData = mensagem.getBytes();
                    System.out.println(coordenadorPort);
                    System.out.println(mensagem + " para: "+ coordenadorPort);
                    sendPacket = new DatagramPacket(sendData, sendData.length,receivePacket.getAddress() , 5555);
                    socket.send(sendPacket);
                    System.out.println(mensagem+ "para: "+ coordenadorPort);
                    System.out.println("Minha porta: "+socket.getLocalPort());
                    socket.receive(receivePacket);
                    String resposta = new String(receivePacket.getData(), receivePacket.getOffset(),receivePacket.getLength());
                    System.out.println(resposta);
               }
           }catch(Exception msg1){
                System.out.println(msg1);
                System.out.println("coornador n responde");
                mensagem = id;
                sendData = mensagem.getBytes();
                try {
                    sendPacket = new DatagramPacket(sendData, sendData.length,InetAddress.getByName(group) , 3333);
                    socket.send(sendPacket);
                    socket.receive(receivePacket); 
                    socket.setSoTimeout(0);
                    mSocket.receive(receivePacket); 
                    String port = new String(receivePacket.getData(), receivePacket.getOffset(),receivePacket.getLength());
                    this.coordenadorPort = Integer.parseInt(port);
                    
                } catch (IOException msg) {
                    System.out.println(" cordenador");
                    
                    mensagem = Integer.toString(socket.getLocalPort());
                    sendData = mensagem.getBytes();
                    try {
                        sendPacket = new DatagramPacket(sendData, sendData.length,InetAddress.getByName(group) , 3333);
                        socket.send(sendPacket);

                    } catch (Exception ex2) {
                        System.out.println("ex2: "+ex2);
                    }
                    new Thread(new Coordenador(mSocket,socket)).start();
                }

           }
        }

        
		
    }

