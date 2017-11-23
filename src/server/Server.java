package server;
import java.io.IOException;
import game.Game;
import game.Data;
import game.JDBCDriver;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Server implements Serializable{
	private static final long serialVersionUID = 1L;

	private JDBCDriver driver = new JDBCDriver();
	//private ArrayList<Game> games = new ArrayList<>();
	private Vector<ServerThread> serverThreads = new Vector<>();
	private Map<String , ServerThread> ips = new HashMap<String, ServerThread>();

	
	public static void main(String[] args) {
		Server sr = new Server();
	}
	
	public Server() {
		try {
			ServerSocket ss = new ServerSocket(6789);
			
			//connecting to clients

			while(true) {
				Socket s = ss.accept();
				System.out.println("Accepted connection from " + s.getInetAddress());
				
				ServerThread st = new ServerThread(s, this);
				serverThreads.add(st);
				ips.put( s.getInetAddress().toString(), st);
				
//				Game newGame = new Game(this);
//				games.add(newGame);
//				try {
//					oos.writeObject(newGame);
//					oos.flush();
//				}catch(IOException ioe) {
//					System.out.println("ioe "+ioe.getMessage());
//				}
//				
			}
		}catch(IOException ioe) {
			System.out.println("ioe in Server: "+ioe.getMessage());
		}
	}
	
	public boolean validate(String user, String pass) {
		return driver.validate(user , pass);
	}
	
	public boolean register(String user, String pass) {
		return driver.register(user,  pass);
	}
	
	public void save(String user, int money, int wins, int losses, int invis, int weakPotion, int strongPotion, int currentSprite, String nickname ) {
		driver.save(user, money, wins, losses, invis, weakPotion, strongPotion, currentSprite, nickname);
	}
	public Data load(String user) {
		return driver.load(user);
	}
	
}