/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenacaogrupos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Mayara
 */
class Servidor implements Runnable {

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
        
    public Servidor(MulticastSocket multiSocket, int coordenadorPort) {
        this.mSocket = mSocket;
        this.coordenadorPort = coordenadorPort;
    }

    @Override
    public void run() {
        while(true){
            try{
                System.out.println(coordenadorPort);
                mSocket.receive(receivePacket);
                String resposta = new String(receivePacket.getData(), receivePacket.getOffset(),receivePacket.getLength());
                System.out.println(resposta);
                String mensagem = Integer.toString(coordenadorPort);
                sendData = mensagem.getBytes();
                DatagramPacket pacote = new DatagramPacket(sendData, sendData.length,receivePacket.getAddress() , receivePacket.getPort());
                socket.send(pacote);
                System.out.println(" Porta do cordenador : " +mensagem+ "para: " + receivePacket.getPort());
            }catch(Exception ex){
                System.out.println(ex);
            }
        }
    }
    
}
