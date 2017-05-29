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


/**
 *
 * @author Mayara
 */
public class Coordenador extends Thread{
    
   	
	    MulticastSocket mSocket;
            byte[] send = new byte[1024];
	    byte[] receiveData = new byte[1024];
            DatagramSocket socket;
            DatagramPacket receivePacket;
            String group = "224.0.0.2";
            int port;
            
                      
            public Coordenador(MulticastSocket mSocket, DatagramSocket socket){
                this.mSocket = mSocket;
                this.socket = socket;
            }

    
                @Override
                public void run() {
                    try{
                    new Thread(new   Servidor(mSocket,socket.getLocalPort())).start();
                        while(true){
                           System.out.println(socket.getLocalPort());
                            socket.receive(receivePacket);
                            String resposta = new String(receivePacket.getData(), receivePacket.getOffset(),receivePacket.getLength());
                            System.out.println(resposta);
                            String mensagem = Integer.toString(socket.getLocalPort());
                            send = mensagem.getBytes();
                            DatagramPacket pacote = new DatagramPacket(send, send.length,receivePacket.getAddress() , receivePacket.getPort());
                            socket.send(pacote);
                            System.out.println(mensagem+ "para: "+receivePacket.getPort());
                        }
                        }catch(Exception ex){
                            System.out.println(ex);
                        }   
                }

				
                }



    

