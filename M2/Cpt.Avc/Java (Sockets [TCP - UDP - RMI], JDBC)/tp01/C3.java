package tp01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class C3 {
	
	private static boolean Premier(int x){
		for(int i=2; i<x; i++){
			if(x%i == 0){
				return false;
			}
		}
		return true;
	}
	
	private static int Produit(int S){
		int P = 1;
		for(int i=2; i<=S; i++){
			if (Premier(i)){
				P *= i;
			}
		}
		return P;
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket SocketServer = new ServerSocket(7777);
		Socket connection = SocketServer.accept();
		System.out.println("Connexion de P2 accept�");
		ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
		int N = (int)in.readObject();
		System.out.println("N est re�u de P2");
		int M = (int)in.readObject();
		System.out.println("M est re�u de P2");
		int S = N+M;
		in.close();
		connection.close();
		int Pr = Produit(S);
		Socket SocketClient = new Socket("localhost", 5555);
		System.out.println("Attente de Connexion a P1");
		ObjectOutputStream out = new ObjectOutputStream(SocketClient.getOutputStream());
		out.writeObject(Pr);
		System.out.println("Pr est envoy� a P1");
		out.writeObject(S);
		System.out.println("S est envoy� a P1");
		out.close();
		SocketClient.close();
	}
}
