package server;

import java.io.IOException;
import game.JDBCDriver;
import game.Data;
import game.Game;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread{
	public static final long serialVersionUID = 1;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Server sr;
	
	
	public static void main (String[] args) {
		
	}
	public ServerThread(Socket s, Server sr) {
		try {
			this.sr = sr;
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
		
			
			Game game = new Game(sr);
			oos.writeObject(game);
			oos.flush();
			this.start();
		}catch(IOException ioe) {
			System.out.println("ioe in ServerThread constructor: " + ioe.getMessage());
		}
	}

	public void run() {
		
//		try {
//			while(true) {
//
//
//			}
//		}catch(IOException ioe) {
//			System.out.println("ioe in ServerThread.run(): "+ioe.getMessage());
//		}catch(ClassNotFoundException ioe) {
//			System.out.println("cnfe: "+ ioe.getMessage());	
//		}
	}
	
	public void sendGame(Game game) {
		try {
			oos.writeObject(game);
			oos.flush();
		}catch(IOException ioe) {
			System.out.println("ioe "+ioe.getMessage());
		}
	}


}
