package server;
import game.Game;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Player extends Thread{
	public static final long serialVersionUID = 1;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Game game;
	
	public static void main(String[] args) {
		Player player = new Player("localhost", 6789);
	}
	public Player(String ip, int port) {
		try {
			Socket s = new Socket(ip, port); 
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());//put outputstream first
			this.start();
			
		}catch(IOException ioe) {
			System.out.println("ioe: "+ ioe.getMessage());
		}
	}
	
	public void run() {
		try {
			while(true) {
				Object obj =ois.readObject();
				if(obj instanceof Game) {
					Game game = (Game) obj;
					this.game = game;
					game.run();
				}
			}
		}catch(IOException ioe) {
			System.out.println("ioe in ChatClient.run(): "+ioe.getMessage());
		}catch(ClassNotFoundException cnfe) {
			System.out.println("cnfe: "+cnfe.getMessage());
		}
	}

}
