package game;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCDriver implements Serializable{
	private static final long serialVersionUID = 1L;
	private static Connection conn = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;
	//private static Statement st = null;
	
	public JDBCDriver() {
		
	}
	
	public static void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamedata?user=root&password=root&useSSL=false");

		} catch (ClassNotFoundException e) {
			//System.out.println("error in class");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			//System.out.println("error in sql");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(){
		try{
			if (rs!=null){
				rs.close();
				rs = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
			if(ps != null ){
				ps = null;
			}
		}catch(SQLException sqle){
			//System.out.println("connection close error");
			sqle.printStackTrace();
		}
	}
	
	public static boolean register(String user, String pwd) {
		connect();
//		System.out.println("user: "+user);
//		System.out.println("pass:" +pwd);
		try {			
			//checks to see if you user exists, if count is 0, not taken.
			//ps = conn.prepareStatement("SELECT COUNT(*) from Users WHERE username=?");
			//st = conn.createStatement();
			ps = conn.prepareStatement("SELECT username FROM Users WHERE username=?");
			ps.setString(1, user);
			rs = ps.executeQuery();
			if(rs.next()) {
				//System.out.println("taken");
				return false;
			}
			//creates MD5 of password
			byte[] bytesOfMessage = pwd.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] theDigest = md.digest(bytesOfMessage);
			String hashedPassword = new String(theDigest);
			
			//inserts username and password and default value of 100 for money
			ps = conn.prepareStatement("INSERT INTO Users(`username`, `password`, `money`)\n"+ "VALUE('"+user+"', '"+hashedPassword+"', '100')");
			ps.execute();
			
			//finding userID of inserted registrant
			ps = conn.prepareStatement("SELECT userID FROM Users WHERE username ='"+user+"'");
			rs = ps.executeQuery();
			int userID = 0;
			if(rs.next()) {
				userID = Integer.parseInt(rs.getString("userID"));
			}	
			//inserts userID along with default username as nickname into settings table
			ps = conn.prepareStatement("INSERT INTO Settings(userID, nickname)\n"+"VALUE('"+userID+"', '"+user+"')");
			ps.execute();
		}catch (SQLException e) {
			System.out.println("e register: "+e.getMessage());
		}catch(UnsupportedEncodingException uee) {
			System.out.println("uee: "+uee.getMessage());
		}catch(NoSuchAlgorithmException nsae) {
			System.out.println("nsae: "+ nsae.getMessage());
		}finally {
			close();
		}
		return true;
	}
	
	//checks if password matches username
	public static boolean validate(String usr, String pwd)
	{
		connect();
		try {
			//System.out.println("Trying to login " + usr + " " + pwd);
			//get stored password of user
			ps = conn.prepareStatement("SELECT password FROM Users WHERE username=?");
			ps.setString(1, usr);
			rs = ps.executeQuery();
			
			//hash md5 of the inputted password
			byte[] bytesOfMessage = pwd.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] theDigest = md.digest(bytesOfMessage);
			String hashedPassword = new String(theDigest);
			
			//if equivalent, true
			if(rs.next())
			{
				//code to view hashed password
//				System.out.println("hashed password: "+ hashedPassword);
//				System.out.println("in table password: " +rs.getString("password"));
				if(hashedPassword.equals(rs.getString("password")) ){
					
					return true;
				}
			}
		}catch (SQLException e) {
			System.out.println("e validate: "+e.getMessage());
		}catch(UnsupportedEncodingException uee) {
			System.out.println("uee: "+uee.getMessage());
		}catch(NoSuchAlgorithmException nsae) {
			System.out.println("nsae: "+ nsae.getMessage());
		}finally{
			close();
		}
		return false;		
	}
	
	public static void save(String user, int money, int wins, int losses, int invis, int weakPotion, int strongPotion, int currentSprite, String nickname ) {
		connect();
		try {
			//get userID of user
			int id=0;
			ps = conn.prepareStatement("SELECT userID FROM Users WHERE username='"+user+"';");
			rs = ps.executeQuery();
			if(rs.next()) {
				id=rs.getInt("userID");
				//System.out.println("got id: "+ id);
			}
			//System.out.println("first update");
			//update USER table of that userID
			ps = conn.prepareStatement("UPDATE Users SET money='"+money+"', wins='"+wins+"', losses='"+losses+"', invisibility='"+invis+"', weakPotion='"+weakPotion+"', strongPotion='"+strongPotion+"' WHERE userID='"+id+"';");
			ps.executeUpdate();
			//System.out.println("second update");
			//update Settings table of that userID
			ps = conn.prepareStatement("UPDATE Settings SET currentSprite='"+currentSprite+"', nickname='"+nickname+"' WHERE userID='"+id+"';");
			ps.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("e save: "+e.getMessage());
		}finally {
			close();
		}
		
	}
	
	public Data load(String user) {
		int money=0, weakPotion=0, strongPotion=0, invis=0, losses=0, wins=0, currentSprite=6;
		String nickname="";
		if(user.equals("guest")||user.equals("")) {
			//do nothing
		}else {
			connect();
			try {
				//get userID of user
				int id=0;
				//System.out.println("first statement");
				ps = conn.prepareStatement("SELECT userID FROM Users WHERE username ='"+user+"'");
				rs = ps.executeQuery();
				if(rs.next()) {
					id=rs.getInt("userID");
					//System.out.println("got id: "+ id);
				}
				//System.out.println("userID");
				//get loading data
				ps= conn.prepareStatement("SELECT money, wins, losses, weakPotion, strongPotion, invisibility FROM Users WHERE userID ='"+id+"';");
				rs = ps.executeQuery();
				//System.out.println("settings");
				if(rs.next()) {
					money = rs.getInt("money");
					wins = rs.getInt("wins");
					losses = rs.getInt("losses");
					weakPotion = rs.getInt("weakPotion");
					strongPotion = rs.getInt("strongPotion");
					invis = rs.getInt("invisibility");

				}

				ps = conn.prepareStatement("SELECT currentSprite, nickname FROM Settings WHERE userID = '"+id+"';");
				rs = ps.executeQuery();
				//System.out.println("last");
				if(rs.next()) {
					currentSprite = rs.getInt("currentSprite");
					nickname = rs.getString("nickname");				
				}

				

			}catch (SQLException e) {
				System.out.println("e load: "+e.getMessage());
			}finally {
				close();
			}


		}
		return new Data(money, wins, losses, invis, weakPotion, strongPotion, currentSprite, nickname);
	}

}
