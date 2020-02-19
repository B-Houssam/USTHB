package tp01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class C1 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket SocketServer = new ServerSocket(5555);
		Scanner sc = new Scanner(System.in);
		System.out.println("Lire N: ");
		int N = sc.nextInt();
		Socket SocketClient = new Socket("localhost", 6666);
		System.out.println("Attente de connexion a P2");
		ObjectOutputStream out = new ObjectOutputStream(SocketClient.getOutputStream());
		out.writeObject(N);
		out.close();
		SocketClient.close();
		System.out.println("N est envoy� a P2");
		Socket connection = SocketServer.accept();
		System.out.println("Connexion de P3 accept�");
		ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
		int Pr = (int)in.readObject();
		System.out.println("Pr est re�u de P3");
		int S = (int)in.readObject();
		System.out.println("S est re�u de P3");
		in.close();
		connection.close();
		System.out.println("Produit: "+Pr);
		System.out.println("Somme: "+S);
	}
}
