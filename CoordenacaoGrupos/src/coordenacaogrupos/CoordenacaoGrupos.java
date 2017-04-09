/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenacaogrupos;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Mayara
 */
public class CoordenacaoGrupos implements Runnable{
    
    private static DatagramSocket server = Coordenador.unicastSocket;

	@Override
	public void run() {
            while (true) {
                byte[] b = new byte[256];
                DatagramPacket packet = new DatagramPacket(b, b.length);
		try {
                    server.receive(packet);
                    String resposta = new String(packet.getData(), packet.getOffset(), packet.getLength(), "UTF-8");
				
		} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
